package za.ac.nwu.ac.logic.flow;

import za.ac.nwu.ac.domain.dto.MemberAccountDto;

import javax.transaction.Transactional;

public interface ModifyMemberAccountFlow {
    @Transactional
    MemberAccountDto subtractMiles(Integer amount, Long memberID, Long accountTypeID);
    @Transactional
    MemberAccountDto addMiles(Integer amount, Long memberID, Long accountTypeID);
}
