package za.ac.nwu.ac.translator;

import za.ac.nwu.ac.domain.dto.MemberAccountDto;

public interface MemberAccountTranslator {
    MemberAccountDto create(MemberAccountDto memberAccount);

    MemberAccountDto getMember(Long memberID, Long accountTypeID);

    MemberAccountDto updateMemberAccount(Integer amount, Long memberId, Long accountTypeId);
}
