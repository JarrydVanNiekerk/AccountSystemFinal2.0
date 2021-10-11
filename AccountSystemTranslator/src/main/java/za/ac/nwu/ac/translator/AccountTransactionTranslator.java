package za.ac.nwu.ac.translator;

import za.ac.nwu.ac.domain.dto.AccountTransactionDto;
import za.ac.nwu.ac.domain.persistence.AccountTransaction;

import java.util.List;

public interface AccountTransactionTranslator {
    List<AccountTransactionDto> getAllTransactions();

    AccountTransactionDto create(AccountTransactionDto accountTransaction);
}
