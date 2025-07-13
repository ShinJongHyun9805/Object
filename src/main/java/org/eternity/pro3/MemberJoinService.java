package org.eternity.pro3;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MemberJoinService {

    private MemberJoinPolicyFactory factory;

    public void join(MemberPostRequestDto requestDto) throws Exception {

        MemberJoinPolicy policy = factory.getMemberJoinType(requestDto);
        long memberSeq = policy.signIn(requestDto);

        addStipulation(requestDto, memberSeq);
        addInflowChannel(requestDto, memberSeq);
        sendJoinMail(requestDto, memberSeq);
    }

    private void addStipulation(MemberPostRequestDto requestDto, long memberSeq) {}

    private void addInflowChannel(MemberPostRequestDto requestDto, long memberSeq) {}

    private void sendJoinMail(MemberPostRequestDto requestDto, long memberSeq) {}
}
