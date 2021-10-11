package za.ac.nwu.ac.logic.flow.impl;

import org.springframework.stereotype.Component;
import za.ac.nwu.ac.domain.dto.AccountTypeDto;
import za.ac.nwu.ac.logic.flow.ModifyAccountTypeFlow;
import za.ac.nwu.ac.translator.AccountTypeTranslator;

import javax.transaction.Transactional;

@Transactional
@Component
public class ModifyAccountTypeFlowImpl implements ModifyAccountTypeFlow {

    public String msg = "Action could not be performed";
    private final AccountTypeTranslator translator;

    public ModifyAccountTypeFlowImpl(AccountTypeTranslator  translator) {
        try {
            this.translator = translator;
        }catch(Exception e){
            throw new RuntimeException(msg);
        }
    }

    @Override
    public AccountTypeDto deleteAccountTypeByMnemonic(String mnemonic) {
        try{
        return translator.deleteAccountTypeByMnemonic(mnemonic);
        }catch(Exception e){
            throw new RuntimeException(msg);
        }
    }

    @Override
    public AccountTypeDto updateAccountType(AccountTypeDto accountType) {
        try{
        return translator.updateAccountType(accountType);
        }catch(Exception e){
            throw new RuntimeException(msg);
        }
    }
}
