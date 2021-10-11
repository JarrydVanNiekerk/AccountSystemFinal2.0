package za.ac.nwu.ac.logic.flow;

import za.ac.nwu.ac.domain.dto.MemberAccountDto;

public interface CreateMemberAccountFlow {
    MemberAccountDto create(MemberAccountDto memberAccount);
}
