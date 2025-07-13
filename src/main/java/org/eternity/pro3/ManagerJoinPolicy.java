package org.eternity.pro3;

public class ManagerJoinPolicy implements MemberJoinPolicy{
    @Override
    public boolean isRepresentative(MemberPostRequestDto dto) {
        return false;
    }

    @Override
    public long signIn(MemberPostRequestDto dto) throws Exception {
        return 0;
    }
}
