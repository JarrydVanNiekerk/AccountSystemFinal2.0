package za.ac.nwu.ac.logic.flow.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import za.ac.nwu.ac.domain.dto.AccountTypeDto;
import za.ac.nwu.ac.logic.flow.ModifyAccountTypeFlow;
import za.ac.nwu.ac.translator.AccountTypeTranslator;

import javax.transaction.Transactional;

@Transactional
@Component
public class ModifyAccountTypeFlowImpl implements ModifyAccountTypeFlow {
    private static final Logger logger = LoggerFactory.getLogger(ModifyMemberAccountFlowImpl.class);
    public String msg = "Action could not be performed";
    private final AccountTypeTranslator translator;

    public ModifyAccountTypeFlowImpl(AccountTypeTranslator  translator) {
        try {
            this.translator = translator;
        }catch(Exception e){
            logger.info("Could not modify account");
            throw new RuntimeException(msg);
        }
    }

    @Override
    public AccountTypeDto deleteAccountTypeByMnemonic(String mnemonic) {
        try{
        return translator.deleteAccountTypeByMnemonic(mnemonic);
        }catch(Exception e){
            logger.info("Could not delete account");
            throw new RuntimeException(msg);
        }
    }

    @Override
    public AccountTypeDto updateAccountType(AccountTypeDto accountType) {
        try{
        return translator.updateAccountType(accountType);
        }catch(Exception e){
            logger.info("Could not update account");
            throw new RuntimeException(msg);
        }
    }
}
