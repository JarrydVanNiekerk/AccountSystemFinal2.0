package za.ac.nwu.ac.logic.flow.impl;

import org.springframework.stereotype.Component;
import za.ac.nwu.ac.domain.dto.AccountTypeDto;
import za.ac.nwu.ac.logic.flow.ModifyAccountTypeFlow;
import za.ac.nwu.ac.translator.AccountTypeTranslator;

import javax.transaction.Transactional;

@Transactional
@Component
public class ModifyAccountTypeFlowImpl implements ModifyAccountTypeFlow {


    private final AccountTypeTranslator translator;

    public ModifyAccountTypeFlowImpl(AccountTypeTranslator  translator) {
        this.translator = translator;
    }

    @Override
    public AccountTypeDto deleteAccountTypeByMnemonic(String mnemonic) {
        return translator.deleteAccountTypeByMnemonic(mnemonic);
    }

    @Override
    public AccountTypeDto updateAccountType(AccountTypeDto accountType) {
        return translator.updateAccountType(accountType);
    }
}
