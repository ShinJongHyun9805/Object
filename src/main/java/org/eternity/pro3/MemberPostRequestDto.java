package org.eternity.pro3;

public class MemberPostRequestDto {
    private long memberCertificationSeq;
    private String certificationYn;
    private String businessManagerHash;

    public long getMemberCertificationSeq() {
        return memberCertificationSeq;
    }

    public void setMemberCertificationSeq(long memberCertificationSeq) {
        this.memberCertificationSeq = memberCertificationSeq;
    }

    public String getCertificationYn() {
        return certificationYn;
    }

    public void setCertificationYn(String certificationYn) {
        this.certificationYn = certificationYn;
    }

    public String getBusinessManagerHash() {

        return businessManagerHash;
    }

    public void setBusinessManagerHash(String businessManagerHash) {
        this.businessManagerHash = businessManagerHash;
    }
}
