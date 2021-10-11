package za.ac.nwu.ac.web.sb.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.nwu.ac.domain.dto.AccountTypeDto;
import za.ac.nwu.ac.domain.service.GeneralResponse;
import za.ac.nwu.ac.logic.flow.FetchAccountTypeFlow;
import za.ac.nwu.ac.logic.flow.CreateAccountTypeFlow;
import za.ac.nwu.ac.logic.flow.ModifyAccountTypeFlow;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("account-type")
public class AccountTypeController {

    private final FetchAccountTypeFlow fetchAccountTypeFlow;
    private final CreateAccountTypeFlow createAccountTypeFlow;
    private final ModifyAccountTypeFlow modifyAccountTypeFlow;

    @Autowired // injects an object or Bean into your class
    public AccountTypeController(FetchAccountTypeFlow fetchAccountTypeFlow,
                                 @Qualifier("createAccountTypeFlowName") CreateAccountTypeFlow createAccountTypeFlow,
                                 ModifyAccountTypeFlow modifyAccountTypeFlow) {
        this.fetchAccountTypeFlow = fetchAccountTypeFlow;
        this.createAccountTypeFlow = createAccountTypeFlow;
        this.modifyAccountTypeFlow = modifyAccountTypeFlow;
    }

    @GetMapping("/all") // fetch all configured account types
    @ApiOperation(value = "Gets all Account types.", notes = "Returns all Account Types")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Account Types Returned", response = GeneralResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = GeneralResponse.class),
            @ApiResponse(code = 404, message = "Not Found", response = GeneralResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = GeneralResponse.class)})
    public ResponseEntity<GeneralResponse<List<AccountTypeDto>>> getAll() {
        List<AccountTypeDto> accountTypes = fetchAccountTypeFlow.getAllAccountTypes();
        GeneralResponse<List<AccountTypeDto>> response = new GeneralResponse<>(true, accountTypes);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("")
    @ApiOperation(value = "Create an Account Type",notes = "Creates an Account Type ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Account created successfully", response = GeneralResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = GeneralResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = GeneralResponse.class)})
    public ResponseEntity<GeneralResponse<AccountTypeDto>> create(
        @ApiParam(value = "Request body to create a new account type",required = true)
        @RequestBody AccountTypeDto accountType) {
        AccountTypeDto accountTypeResponse = createAccountTypeFlow.create(accountType);
        GeneralResponse<AccountTypeDto> response = new GeneralResponse<>(true,accountTypeResponse);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @GetMapping("{mnemonic}") // fetch all configured account types
    @ApiOperation(value = "Fetch specified Account Type", notes = "Fetches the Account Type")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Goal Found", response = GeneralResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = GeneralResponse.class),
            @ApiResponse(code = 404, message = "Resource Not Found", response = GeneralResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = GeneralResponse.class)})
    public ResponseEntity<GeneralResponse<AccountTypeDto>> getAccountType(
            @ApiParam(value = "Uniquely identifies the account type",example = "Discovery Miles",
                    name = "mnemonic",required = true)
            @PathVariable("mnemonic") final String mnemonic
    ) {
        AccountTypeDto accountType = fetchAccountTypeFlow.getAccountTypeByMnemonic(mnemonic);
        GeneralResponse<AccountTypeDto> response = new GeneralResponse<>(true, accountType);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("{mnemonic}")
    @ApiOperation(value="Updates Account type.",notes = "Updates Account type")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Account Type Successfully Created", response = GeneralResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = GeneralResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = GeneralResponse.class)})

    public ResponseEntity<GeneralResponse<AccountTypeDto>> updateAccountType(
            @ApiParam(value = "Uniquely identifies the AccountType.",
                    example = "Discovery Miles",
                    name = "mnemonic",
                    required = true)
            @PathVariable("mnemonic") final String mnemonic,
            @ApiParam(value = "The new AccountTypeName  ",
                    example = "Discovery Milesv2.0",
                    name = "newAccountTypeName",
                    required = true)
            @RequestParam(value ="newAccountTypeName") final String newAccountTypeName,
            @ApiParam(value = "Creation date in ISO format. (yyy-MM-dd) \n \r If empty or null, value will not be updated ",
                    name = "newCreationDate")
            @RequestParam(value ="newCreationDate", required = false)
            LocalDate newCreationDate) {
        AccountTypeDto tempDto = fetchAccountTypeFlow.getAccountTypeByMnemonic(mnemonic);
        if(newCreationDate == null){
            newCreationDate = tempDto.getCreationDate();
        }
        AccountTypeDto accountType = new AccountTypeDto(mnemonic, newAccountTypeName, newCreationDate);
        AccountTypeDto accountTypeResponse = modifyAccountTypeFlow.updateAccountType(accountType);
        GeneralResponse<AccountTypeDto> response = new GeneralResponse<>(true, accountTypeResponse);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}