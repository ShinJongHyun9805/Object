package org.eternity.pro3;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MemberJoinPolicyFactory {

    private final List<MemberJoinPolicy> policies;

    public MemberJoinPolicyFactory(List<MemberJoinPolicy> policies) {
        this.policies = policies;
    }

    public MemberJoinPolicy getMemberJoinType(MemberPostRequestDto requestDto) {
        return policies.stream()
                .filter(p -> p.isRepresentative(requestDto))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("적합한 회원 가입 정책이 존재하지 않습니다."));
    }
}
