package org.eternity.pro3;

public abstract class AbstractJoinPolicy implements MemberJoinPolicy {

    protected final MbrCertificationDao mbrCertificationDao;
    protected final MbrMemberDao mbrMemberDao;
    protected final PasswordEncoder passwordEncoder;
    protected final PersonalInfoManager personalInfoManager;

    protected AbstractJoinPolicy(MbrCertificationDao mbrCertificationDao, MbrMemberDao mbrMemberDao, PasswordEncoder passwordEncoder, PersonalInfoManager personalInfoManager) {
        this.mbrCertificationDao = mbrCertificationDao;
        this.mbrMemberDao = mbrMemberDao;
        this.passwordEncoder = passwordEncoder;
        this.personalInfoManager = personalInfoManager;
    }

    protected MbrCertificationEntity validateCertification(MemberPostRequestDto requestDto) {
        boolean debugStatus = true;
        MbrCertificationEntity certification = debugStatus
                ? mbrCertificationDao.getCertification(requestDto.getMemberCertificationSeq())
                : mbrCertificationDao.getCertificationBefore5minute(requestDto.getMemberCertificationSeq());

        return certification;
    }

    public abstract long createMember(MemberPostRequestDto requestDto, MbrCertificationEntity certification, String mobilePhoneHash);
}
