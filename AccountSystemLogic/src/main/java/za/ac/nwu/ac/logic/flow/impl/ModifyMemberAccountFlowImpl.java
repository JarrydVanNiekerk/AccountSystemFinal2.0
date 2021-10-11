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

    public ModifyMemberAccountFlowImpl(MemberAccountTranslator translator) {
        this.translator = translator;
    }

    @Transactional
    @Override
    public MemberAccountDto subtractMiles(Integer amount, Long memberID, Long accountTypeID) {

        if(amount>0) {
            amount=amount * -1;
        }
        logger.info("Adding to Member Miles: " + "\n\tAmount = {}" +
                "\n\tMember ID = {}" + "\n\tAccount Type ID = {}", amount,memberID,accountTypeID);
        Integer oldBal= 0;
        Integer newBal= 0;
        oldBal= translator.getMember(memberID,accountTypeID).getBalance();
        if(amount + oldBal >=0){
            logger.info("Valid Transaction");
            newBal = amount + oldBal;
            MemberAccountDto result =translator.updateMemberAccount(newBal, memberID, accountTypeID);
            logger.info("The Member Account was updated: {}",result);
            return result;
        } else {
            logger.info("Cannot subtract values larger than current account balance");
            throw new RuntimeException("Unable to Update the database");
        }
    }
    
    @Transactional
    @Override
    public MemberAccountDto addMiles(Integer amount, Long memberID, Long accountTypeID) {

        if(amount<0) {
            amount = amount * -1;
        }
        logger.info("Subtracting Miles from account: " + "\n\tAmount = {}" + "\n\tMember ID = {}" +
                "\n\tAccount Type ID = {}", amount,memberID,accountTypeID);
        Integer oldBal= 0;
        Integer newBal= 0;
        oldBal= translator.getMember(memberID,accountTypeID).getBalance();
        newBal = amount + oldBal;
        MemberAccountDto result =translator.updateMemberAccount(newBal, memberID, accountTypeID);
        logger.info("The Member Account was updated: {}",result);
        return result;
    }

}
