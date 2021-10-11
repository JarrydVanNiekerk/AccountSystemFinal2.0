package za.ac.nwu.ac.logic.flow;

import za.ac.nwu.ac.domain.dto.MemberAccountDto;

public interface FetchMemberAccountFlow {
    MemberAccountDto getMember(Long memberId, Long accountTypeId);
}
