package org.eternity.pro3;

public interface MemberJoinPolicy {

    boolean isRepresentative(MemberPostRequestDto dto);

    long signIn(MemberPostRequestDto dto) throws Exception;
}
