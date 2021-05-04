package dto.fssp;

import java.util.Objects;

public class FsspInfo {

    private String ipDate;
    private String ipNumber;
    private String ipDocument;
    private String fsspDepartment;
    private String fsspAdress;
    private String debtSumm;

    public FsspInfo(String ipDate, String ipNumber, String ipDocument, String fsspDepartment, String fsspAdress, String debtSumm) {
        this.ipDate = ipDate;
        this.ipNumber = ipNumber;
        this.ipDocument = ipDocument;
        this.fsspDepartment = fsspDepartment;
        this.fsspAdress = fsspAdress;
        this.debtSumm = debtSumm;
    }

    public String getIpDate() {
        return ipDate;
    }

    public void setIpDate(String ipDate) {
        this.ipDate = ipDate;
    }

    public String getIpNumber() {
        return ipNumber;
    }

    public void setIpNumber(String ipNumber) {
        this.ipNumber = ipNumber;
    }

    public String getIpDocument() {
        return ipDocument;
    }

    public void setIpDocument(String ipDocument) {
        this.ipDocument = ipDocument;
    }

    public String getFsspDepartment() {
        return fsspDepartment;
    }

    public void setFsspDepartment(String fsspDepartment) {
        this.fsspDepartment = fsspDepartment;
    }

    public String getFsspAdress() {
        return fsspAdress;
    }

    public void setFsspAdress(String fsspAdress) {
        this.fsspAdress = fsspAdress;
    }

    public String getDebtSumm() {
        return debtSumm;
    }

    public void setDebtSumm(String debtSumm) {
        this.debtSumm = debtSumm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FsspInfo)) return false;
        FsspInfo fsspInfo = (FsspInfo) o;
        return Objects.equals(getIpDate(), fsspInfo.getIpDate()) && Objects.equals(getIpNumber(), fsspInfo.getIpNumber()) && Objects.equals(getIpDocument(), fsspInfo.getIpDocument()) && Objects.equals(getFsspDepartment(), fsspInfo.getFsspDepartment()) && Objects.equals(getFsspAdress(), fsspInfo.getFsspAdress()) && Objects.equals(getDebtSumm(), fsspInfo.getDebtSumm());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIpDate(), getIpNumber(), getIpDocument(), getFsspDepartment(), getFsspAdress(), getDebtSumm());
    }


    @Override
    public String toString() {
        return "FsspInfo{" +
                "ipDate='" + ipDate + '\'' +
                ", ipNumber='" + ipNumber + '\'' +
                ", ipDocument='" + ipDocument + '\'' +
                ", fsspDepartment='" + fsspDepartment + '\'' +
                ", fsspAdress='" + fsspAdress + '\'' +
                ", debtSumm='" + debtSumm + '\'' +
                '}';
    }
}
