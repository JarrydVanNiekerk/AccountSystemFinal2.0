package za.ac.nwu.ac.logic.flow.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import za.ac.nwu.ac.domain.dto.MemberAccountDto;
import za.ac.nwu.ac.logic.flow.ModifyMemberAccountFlow;
import za.ac.nwu.ac.translator.MemberAccountTranslator;

import javax.transaction.Transactional;

@Transactional
@Component("modifyMemberAccountFlowName")
public class ModifyMemberAccountFlowImpl implements ModifyMemberAccountFlow {

    private static final Logger logger = LoggerFactory.getLogger(ModifyMemberAccountFlowImpl.class);
    private final MemberAccountTranslator translator;
    public String msg = "Account could not be updated";
    public ModifyMemberAccountFlowImpl(MemberAccountTranslator translator) {
        this.translator = translator;
    }

    @Transactional
    @Override
    public MemberAccountDto subtractMiles(Integer amount, Long memberID, Long accountTypeID) {
        try {
            if (amount > 0) {
                amount = amount * -1;
            }
            logger.info("Adding Miles: " + "\n\tAmount = {}" +
                    "\n\tMember ID = {}" + "\n\tAccount Type ID = {}", amount, memberID, accountTypeID);
            Integer oldBal = 0;
            Integer newBal = 0;
            oldBal = translator.getMember(memberID, accountTypeID).getBalance();
            if (amount + oldBal >= 0) {
                logger.info("Transaction Successful");
                newBal = amount + oldBal;
                MemberAccountDto result = translator.updateMemberAccount(newBal, memberID, accountTypeID);
                logger.info("Account updated successfully: {}", result);
                return result;
            }else{
                throw new RuntimeException(msg);
            }
        } catch (Exception e) {
            logger.info("Please ensure that the values are larger than the current balance");
            throw new RuntimeException(msg);
        }
    }
    
    @Transactional
    @Override
    public MemberAccountDto addMiles(Integer amount, Long memberID, Long accountTypeID) {
        try {
            if (amount < 0) {
                amount = amount * -1;
            }
            logger.info("Subtracting Miles: " + "\n\tAmount = {}" + "\n\tMember ID = {}" +
                    "\n\tAccount Type ID = {}", amount, memberID, accountTypeID);
            Integer oldBal = 0;
            Integer newBal = 0;
            oldBal = translator.getMember(memberID, accountTypeID).getBalance();
            newBal = amount + oldBal;
            MemberAccountDto result = translator.updateMemberAccount(newBal, memberID, accountTypeID);
            logger.info("Account updated: {}", result);
            return result;
        } catch(Exception e) {
            logger.info("Enter a valid number");
            throw new RuntimeException(msg);
        }
    }

}
