package za.ac.nwu.ac.web.sb.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.nwu.ac.domain.dto.MemberAccountDto;
import za.ac.nwu.ac.domain.service.GeneralResponse;
import za.ac.nwu.ac.logic.flow.CreateMemberAccountFlow;
import za.ac.nwu.ac.logic.flow.FetchMemberAccountFlow;
import za.ac.nwu.ac.logic.flow.ModifyMemberAccountFlow;

@RestController
@RequestMapping("member-account")
public class MemberAccountController {
    private static final Logger logger = LoggerFactory.getLogger(MemberAccountController.class);
    private final CreateMemberAccountFlow createMemberAccountFlow;
    private final FetchMemberAccountFlow fetchMemberAccountFlow;
    private final ModifyMemberAccountFlow modifyMemberAccountFlow;

    @Autowired
    public MemberAccountController(@Qualifier("createMemberAccountFlowName") CreateMemberAccountFlow createMemberAccountFlow,
                                   @Qualifier("fetchMemberAccountFlowName") FetchMemberAccountFlow fetchMemberAccountFlow,
                                   @Qualifier("modifyMemberAccountFlowName") ModifyMemberAccountFlow modifyMemberAccountFlow) {
        this.createMemberAccountFlow = createMemberAccountFlow;
        this.fetchMemberAccountFlow = fetchMemberAccountFlow;
        this.modifyMemberAccountFlow = modifyMemberAccountFlow;
    }

    @PostMapping("")
    @ApiOperation(value = "Create a new Member Account of a specific AccountType")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Account Type Successfully Created", response = GeneralResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = GeneralResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = GeneralResponse.class)})
    public ResponseEntity<GeneralResponse<MemberAccountDto>> create(
            @ApiParam(value = "Request body to create a new Member Account", required = true)
            @RequestBody MemberAccountDto MemberAccount ){
        MemberAccountDto MemberAccountResponse = createMemberAccountFlow.create(MemberAccount);
        GeneralResponse<MemberAccountDto> response = new GeneralResponse<>(true, MemberAccountResponse);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("{memberId}/{accountTypeId}")
    @ApiOperation(value="Gets a MemberAccount for specified MemberID and AccountTypeID",
            notes = "Gets a MemberAccount for specified MemberID and AccountTypeID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Account Types Returned", response = GeneralResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = GeneralResponse.class),
            @ApiResponse(code = 404, message = "Not Found", response = GeneralResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = GeneralResponse.class)
    })
    public ResponseEntity<GeneralResponse<MemberAccountDto>> getMember(
            @ApiParam(value = "The MemberID that uniquely identifies the MemberAccountOwner.",
                    name = "memberId",
                    example = "110",
                    required = true)
            @PathVariable("memberId") final String memberId,
            @ApiParam(value = "The AccountTypeID that uniquely identifies the AccountType.",
                    name = "accountTypeId",
                    example = "2",
                    required = true)
            @PathVariable("accountTypeId") final String accountTypeId) {
        Long member = Long.valueOf(0);
        Long account = Long.valueOf(0);
        try{
            member = Long.parseLong(memberId);
            account = Long.parseLong(accountTypeId);
        } catch (NumberFormatException e) {
            logger.error("Parses Failed", e);
            throw new RuntimeException("Unable to format types",e);
        }
        logger.info("Attempting to find User Account with properties: " +
                "\nAccountTypeID = {}" +
                "\nMemberID = {}",accountTypeId,memberId);
        MemberAccountDto memberAccount = fetchMemberAccountFlow.getMember(member,account);
        GeneralResponse<MemberAccountDto> response = new GeneralResponse<>(true, memberAccount);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("subtract/{subtractVal}")
    @ApiOperation(value = "Decreases a UserAccount with the value of a transaction",
            notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Account Type Successfully Created", response = GeneralResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = GeneralResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = GeneralResponse.class)
    })
    public ResponseEntity<GeneralResponse<MemberAccountDto>> subtractMiles(
            @ApiParam(value="Transaction Value",
                    name="subtractVal",
                    example = "550",
                    required = true)
            @PathVariable("subtractVal") final String transactionValue,

            @ApiParam(value = "The MemberID that uniquely identifies the UserAccountOwner.",
                    name = "memberId",
                    example = "110",
                    required = true)
            @RequestParam("memberId") final Long memberId,

            @ApiParam(value = "The AccountTypeID that uniquely identifies the AccountType.",
                    name="accountTypeId",
                    example = "2",
                    required = true)
            @RequestParam("accountTypeId") final Long accountTypeId
    ){
        Integer intToPass =0;
        try {
            intToPass =Integer.parseInt(transactionValue);
        } catch (NumberFormatException e){
            logger.error("TransactionValue Parse Failed", e);
        }
        logger.info("Value of TransactionValue {}",transactionValue);
        logger.info("Value of MemberID {}",memberId);
        logger.info("Value of AccountTypeID {}",accountTypeId);
        MemberAccountDto memberAccount = modifyMemberAccountFlow.subtractMiles(intToPass,memberId,accountTypeId);
        logger.info("Update Operation Completed Successfully");
        GeneralResponse<MemberAccountDto> response = new GeneralResponse<>(true, memberAccount);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("add/{addVal}")
    @ApiOperation(value = "Increases a UserAccount with the value of a transaction",
            notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Account Type Successfully Created", response = GeneralResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = GeneralResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = GeneralResponse.class)
    })
    public ResponseEntity<GeneralResponse<MemberAccountDto>> addMiles(
            @ApiParam(value="Transaction Value",
                    name="addVal",
                    example = "550",
                    required = true)
            @PathVariable("addVal") final String transactionValue,
            @ApiParam(value = "The MemberID that uniquely identifies the UserAccountOwner.",
                    name = "memberId",
                    example = "110",
                    required = true)
            @RequestParam("memberId") final Long memberId,
            @ApiParam(value = "The AccountTypeID that uniquely identifies the AccountType.",
                    name="accountTypeId",
                    example = "2",
                    required = true)
            @RequestParam("accountTypeId") final Long accountTypeId
    ){
        Integer transactVal =0;
        try{
            transactVal = Integer.parseInt(transactionValue);
        } catch (NumberFormatException e){
            logger.error("TransactionValue Parse Failed", e);
        }
        logger.info("Transaction Value: {}",transactionValue);
        logger.info("Member ID: {}",memberId);
        logger.info("Account Type ID {}",accountTypeId);
        MemberAccountDto memberAccount = modifyMemberAccountFlow.addMiles(transactVal,memberId ,accountTypeId);
        logger.info("Update Operation Completed Successfully!");
        GeneralResponse<MemberAccountDto> response = new GeneralResponse<>(true, memberAccount);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
