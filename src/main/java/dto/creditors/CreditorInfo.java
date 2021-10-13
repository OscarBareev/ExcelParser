package dto.creditors;

import java.util.Objects;

public class CreditorInfo {

    private String num;
    private String creditorName;
    private String creditorAddress;
    private String sum;
    private String contractReq;
    private String actReq;
    private String sumAct;
    private String contractClaimReq;
    private String actClaimReq;
    private String sumClaimAct;


    public CreditorInfo(String num, String creditorName, String creditorAddress, String sum, String contractReq, String actReq, String sumAct, String contractClaimReq, String actClaimReq, String sumClaimAct) {
        this.num = num;
        this.creditorName = creditorName;
        this.creditorAddress = creditorAddress;
        this.sum = sum;
        this.contractReq = contractReq;
        this.actReq = actReq;
        this.sumAct = sumAct;
        this.contractClaimReq = contractClaimReq;
        this.actClaimReq = actClaimReq;
        this.sumClaimAct = sumClaimAct;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getCreditorName() {
        return creditorName;
    }

    public void setCreditorName(String creditorName) {
        this.creditorName = creditorName;
    }

    public String getCreditorAddress() {
        return creditorAddress;
    }

    public void setCreditorAddress(String creditorAddress) {
        this.creditorAddress = creditorAddress;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public String getContractReq() {
        return contractReq;
    }

    public void setContractReq(String contractReq) {
        this.contractReq = contractReq;
    }

    public String getActReq() {
        return actReq;
    }

    public void setActReq(String actReq) {
        this.actReq = actReq;
    }

    public String getSumAct() {
        return sumAct;
    }

    public void setSumAct(String sumAct) {
        this.sumAct = sumAct;
    }

    public String getContractClaimReq() {
        return contractClaimReq;
    }

    public void setContractClaimReq(String contractClaimReq) {
        this.contractClaimReq = contractClaimReq;
    }

    public String getActClaimReq() {
        return actClaimReq;
    }

    public void setActClaimReq(String actClaimReq) {
        this.actClaimReq = actClaimReq;
    }

    public String getSumClaimAct() {
        return sumClaimAct;
    }

    public void setSumClaimAct(String sumClaimAct) {
        this.sumClaimAct = sumClaimAct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreditorInfo)) return false;
        CreditorInfo that = (CreditorInfo) o;
        return Objects.equals(getCreditorName(), that.getCreditorName()) && Objects.equals(getCreditorAddress(), that.getCreditorAddress()) && Objects.equals(getSum(), that.getSum()) && Objects.equals(getContractReq(), that.getContractReq()) && Objects.equals(getActReq(), that.getActReq()) && Objects.equals(getSumAct(), that.getSumAct()) && Objects.equals(getContractClaimReq(), that.getContractClaimReq()) && Objects.equals(getActClaimReq(), that.getActClaimReq()) && Objects.equals(getSumClaimAct(), that.getSumClaimAct());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCreditorName(), getCreditorAddress(), getSum(), getContractReq(), getActReq(), getSumAct(), getContractClaimReq(), getActClaimReq(), getSumClaimAct());
    }
}
