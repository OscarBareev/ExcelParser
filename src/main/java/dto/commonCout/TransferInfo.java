package dto.commonCout;

import java.util.Objects;

public class TransferInfo {

    private String index;
    private String courtName;
    private String courtAddress;
    private String claimerName;
    private String caseNum;

    public TransferInfo(String index, String courtName, String courtAddress, String claimerName, String caseNum) {
        this.index = index;
        this.courtName = courtName;
        this.courtAddress = courtAddress;
        this.claimerName = claimerName;
        this.caseNum = caseNum;
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

    public String getCaseNum() {
        return caseNum;
    }

    public void setCaseNum(String caseNum) {
        this.caseNum = caseNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TransferInfo)) return false;
        TransferInfo that = (TransferInfo) o;
        return Objects.equals(getIndex(), that.getIndex()) && Objects.equals(getCourtName(), that.getCourtName()) && Objects.equals(getCourtAddress(), that.getCourtAddress()) && Objects.equals(getClaimerName(), that.getClaimerName()) && Objects.equals(getCaseNum(), that.getCaseNum());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIndex(), getCourtName(), getCourtAddress(), getClaimerName(), getCaseNum());
    }

    @Override
    public String toString() {
        return "index='" + index + '\'' +
                ", courtName='" + courtName + '\'' +
                ", courtAddress='" + courtAddress + '\'' +
                ", claimerName='" + claimerName + '\'' +
                ", caseNum='" + caseNum + "\n";
    }
}
