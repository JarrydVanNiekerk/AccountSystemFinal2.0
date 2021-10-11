package za.ac.nwu.ac.logic.flow.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.ac.domain.dto.AccountTypeDto;
import za.ac.nwu.ac.domain.persistence.AccountType;
import za.ac.nwu.ac.logic.flow.FetchAccountTypeFlow;
import za.ac.nwu.ac.translator.AccountTypeTranslator;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Component
public class FetchAccountTypeFlowImpl implements FetchAccountTypeFlow {
    private final AccountTypeTranslator accountTypeTranslator;
    private static final Logger logger = LoggerFactory.getLogger(ModifyMemberAccountFlowImpl.class);
    public String msg = "Action could not be performed";
    @Autowired
    public FetchAccountTypeFlowImpl(AccountTypeTranslator accountTypeTranslator) {
        try {
            this.accountTypeTranslator = accountTypeTranslator;
        }catch(Exception e){
            logger.info("Account translator error");
            throw new RuntimeException(msg);
        }
    }

    @Override
    public List<AccountTypeDto> getAllAccountTypes() {
        try{
        return accountTypeTranslator.getAllAccountTypes();
        }catch(Exception e){
            logger.info("Account translator error, could not fecth types");
            throw new RuntimeException(msg);
        }
    }

    @Override
    public AccountTypeDto getAccountTypeByMnemonic(String mnemonic) {
        try{
        return accountTypeTranslator.getAccountTypeByMnemonic(mnemonic);
        }catch(Exception e){
            logger.info("Account translator error, could not fetch mnemonic");
            throw new RuntimeException(msg);
        }
    }

}
