package za.ac.nwu.ac.translator.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.ac.domain.dto.AccountTransactionDto;
import za.ac.nwu.ac.domain.dto.MemberAccountDto;
import za.ac.nwu.ac.domain.persistence.MemberAccount;
import za.ac.nwu.ac.repo.persistence.MemberAccountRepository;
import za.ac.nwu.ac.translator.AccountTransactionTranslator;
import za.ac.nwu.ac.translator.MemberAccountTranslator;

import javax.transaction.Transactional;


@Component
public class MemberAccountTranslatorImpl implements MemberAccountTranslator {

    private final MemberAccountRepository repo;

    @Autowired
    public MemberAccountTranslatorImpl(MemberAccountRepository repo) {
        this.repo = repo;
    }

    @Override
    public MemberAccountDto create(MemberAccountDto MemberAccountDto) {
        try{
            MemberAccount MemberAccount= repo.save(MemberAccountDto.getMemberAccount());
            return new MemberAccountDto(MemberAccount);
        }catch (Exception e){
            throw  new RuntimeException("Unable to save to the DB", e);
        }
    }

    @Override
    @Transactional
    public MemberAccountDto updateMemberAccount(Integer amount, Long memberId, Long accountTypeId) {
        try {
            MemberAccount memberAccount = new MemberAccount(amount,memberId,accountTypeId);
            repo.updateMemberAccount(amount,memberId,accountTypeId);
            return new MemberAccountDto(memberAccount);
        } catch (Exception e){
            throw new RuntimeException("Unable to update DB", e);
        }
    }

    @Override
    public MemberAccountDto getMember(Long memberID, Long accountTypeID) {
        try {
            MemberAccount memberAccount = repo.getMember(memberID, accountTypeID);
            return new MemberAccountDto(memberAccount);
        } catch (Exception e){
            throw new RuntimeException("Unable to read from the DB", e);
        }
    }

}