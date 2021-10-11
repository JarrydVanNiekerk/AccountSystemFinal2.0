package za.ac.nwu.ac.translator.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.ac.domain.dto.AccountTransactionDto;
import za.ac.nwu.ac.domain.persistence.AccountTransaction;
import za.ac.nwu.ac.repo.persistence.AccountTransactionRepository;
import za.ac.nwu.ac.translator.AccountTransactionTranslator;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountTransactionTranslatorImpl implements AccountTransactionTranslator {
    private final AccountTransactionRepository repo;

    @Autowired
    public AccountTransactionTranslatorImpl(AccountTransactionRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<AccountTransactionDto> getAllTransactions()
    {
        List<AccountTransactionDto> accountTransactionDto = new ArrayList<>();
        try{
            for (AccountTransaction accountTransaction : repo.findAll()){
                accountTransactionDto.add(new AccountTransactionDto(accountTransaction));
            }
        } catch (Exception e) {
            throw new RuntimeException("Unable to read from the DB", e);
        }
        return accountTransactionDto;
    }

    @Override
    public AccountTransactionDto create(AccountTransactionDto accountTransactionDto)
    {
        try{
            AccountTransaction accountTransaction = repo.save(accountTransactionDto.getTransaction());
            return new AccountTransactionDto(accountTransaction);
        } catch (Exception e){
            throw new RuntimeException("Unable to save to the DB", e);
        }

    }
}
