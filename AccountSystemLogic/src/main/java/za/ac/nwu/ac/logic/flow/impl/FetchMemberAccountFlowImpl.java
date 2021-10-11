package za.ac.nwu.ac.logic.flow.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import za.ac.nwu.ac.domain.dto.MemberAccountDto;
import za.ac.nwu.ac.logic.flow.FetchMemberAccountFlow;
import za.ac.nwu.ac.translator.MemberAccountTranslator;

import javax.transaction.Transactional;

@Transactional
@Component("fetchMemberAccountFlowName")
public class FetchMemberAccountFlowImpl implements FetchMemberAccountFlow {

    private static final Logger logger = LoggerFactory.getLogger(ModifyMemberAccountFlowImpl.class);
    private final MemberAccountTranslator translator;
    public String msg = "Action could not be performed";

    public FetchMemberAccountFlowImpl(MemberAccountTranslator translator) {
        this.translator = translator;
    }

    @Override

    public MemberAccountDto getMember(Long memberId, Long accountTypeId) {
        try {
            return translator.getMember(memberId, accountTypeId);
        }catch(Exception e){
            logger.info("could not fetch member");
            throw new RuntimeException(msg);
        }
    }


}
