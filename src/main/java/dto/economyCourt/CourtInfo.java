package dto.economyCourt;

import java.util.Objects;

public class CourtInfo {

    private String index;
    private String courtName;
    private String courtAddress;
    private String claimerName;
    private String claimerOGRN;
    private String claimerINN;
    private String caseNum;
    private String judge;
    private String caseType;

    public CourtInfo(
            String index,
            String courtName,
            String courtAddress,
            String claimerName,
            String claimerOGRN,
            String claimerINN,
            String caseNum,
            String judge,
            String caseType) {
        this.index = index;
        this.courtName = courtName;
        this.courtAddress = courtAddress;
        this.claimerName = claimerName;
        this.claimerOGRN = claimerOGRN;
        this.claimerINN = claimerINN;
        this.caseNum = caseNum;
        this.judge = judge;
        this.caseType = caseType;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getCourtName() {
        return courtName;
    }

    public void setCourtName(String courtName) {
        this.courtName = courtName;
    }

    public String getCourtAddress() {
        return courtAddress;
    }

    public void setCourtAddress(String courtAddress) {
        this.courtAddress = courtAddress;
    }

    public String getClaimerName() {
        return claimerName;
    }

    public void setClaimerName(String claimerName) {
        this.claimerName = claimerName;
    }

    public String getClaimerOGRN() {
        return claimerOGRN;
    }

    public void setClaimerOGRN(String claimerOGRN) {
        this.claimerOGRN = claimerOGRN;
    }

    public String getClaimerINN() {
        return claimerINN;
    }

    public void setClaimerINN(String claimerINN) {
        this.claimerINN = claimerINN;
    }

    public String getCaseNum() {
        return caseNum;
    }

    public void setCaseNum(String caseNum) {
        this.caseNum = caseNum;
    }

    public String getJudge() {
        return judge;
    }

    public void setJudge(String judge) {
        this.judge = judge;
    }

    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CourtInfo)) return false;
        CourtInfo courtInfo = (CourtInfo) o;
        return Objects.equals(getIndex(), courtInfo.getIndex()) && Objects.equals(getCourtName(), courtInfo.getCourtName()) && Objects.equals(getCourtAddress(), courtInfo.getCourtAddress()) && Objects.equals(getClaimerName(), courtInfo.getClaimerName()) && Objects.equals(getClaimerOGRN(), courtInfo.getClaimerOGRN()) && Objects.equals(getClaimerINN(), courtInfo.getClaimerINN()) && Objects.equals(getCaseNum(), courtInfo.getCaseNum()) && Objects.equals(getJudge(), courtInfo.getJudge()) && Objects.equals(getCaseType(), courtInfo.getCaseType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIndex(), getCourtName(), getCourtAddress(), getClaimerName(), getClaimerOGRN(), getClaimerINN(), getCaseNum(), getJudge(), getCaseType());
    }

    @Override
    public String toString() {
        return
                "index='" + index + '\'' +
                ", courtName='" + courtName + '\'' +
                ", courtAddress='" + courtAddress + '\'' +
                ", claimerName='" + claimerName + '\'' +
                ", claimerOGRN='" + claimerOGRN + '\'' +
                ", claimerINN='" + claimerINN + '\'' +
                ", caseNum='" + caseNum + '\'' +
                ", judge='" + judge + '\'' +
                ", caseType='" + caseType + "\n";
    }
}
