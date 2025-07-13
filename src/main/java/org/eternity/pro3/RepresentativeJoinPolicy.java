package org.eternity.pro3;

public class RepresentativeJoinPolicy extends AbstractJoinPolicy {

    private final CcsBusinessDao ccsBusinessDao;
    private final MemberManager memberManager;
    private final BusinessRegistrationManager businessRegistrationManager;
    private final CcsBusinessManagerDao businessManagerDao;
    private final MbrMemberRoleDao memberRoleDao;

    public RepresentativeJoinPolicy(
            MbrCertificationDao mbrCertificationDao, CcsBusinessDao ccsBusinessDao,
            MemberManager memberManager, MbrMemberDao mbrMemberDao,
            PasswordEncoder passwordEncoder, PersonalInfoManager personalInfoManager,
            BusinessRegistrationManager businessRegistrationManager, CcsBusinessManagerDao businessManagerDao, MbrMemberRoleDao memberRoleDao) {
        super(mbrCertificationDao, mbrMemberDao, passwordEncoder, personalInfoManager);
        this.ccsBusinessDao = ccsBusinessDao;
        this.memberManager = memberManager;
        this.businessRegistrationManager = businessRegistrationManager;
        this.businessManagerDao = businessManagerDao;
        this.memberRoleDao = memberRoleDao;
    }


    @Override
    public boolean isRepresentative(MemberPostRequestDto requestDto) {
        return requestDto.getCertificationYn().equals("Y") &&
                requestDto.getBusinessManagerHash() != null;
    }

    @Override
    public long signIn(MemberPostRequestDto requestDto) throws Exception {

        MbrCertificationEntity certification = validateCertification(requestDto);
        String phoneHash = certification.getRealNameCertificationPhone();
        long memberSeq = createMember(requestDto, certification, phoneHash);

        return 0;
    }

    @Override
    public long createMember(MemberPostRequestDto requestDto, MbrCertificationEntity certification, String mobilePhoneHash) {
        return 0;
    }
}
