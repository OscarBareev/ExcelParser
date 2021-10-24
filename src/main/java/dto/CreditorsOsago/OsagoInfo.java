package dto.CreditorsOsago;

import java.util.Objects;

public class OsagoInfo {

    private String num;
    private String index;
    private String creditorName;
    private String creditorAddress;
    private String policeOsago;
    private String policeKasko;

    public OsagoInfo(String num, String index, String creditorName, String creditorAddress, String policeOsago, String policeKasko) {
        this.num = num;
        this.index = index;
        this.creditorName = creditorName;
        this.creditorAddress = creditorAddress;
        this.policeOsago = policeOsago;
        this.policeKasko = policeKasko;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
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

    public String getPoliceOsago() {
        return policeOsago;
    }

    public void setPoliceOsago(String policeOsago) {
        this.policeOsago = policeOsago;
    }

    public String getPoliceKasko() {
        return policeKasko;
    }

    public void setPoliceKasko(String policeKasko) {
        this.policeKasko = policeKasko;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OsagoInfo osagoInfo = (OsagoInfo) o;
        return Objects.equals(num, osagoInfo.num) && Objects.equals(index, osagoInfo.index) && Objects.equals(creditorName, osagoInfo.creditorName) && Objects.equals(creditorAddress, osagoInfo.creditorAddress) && Objects.equals(policeOsago, osagoInfo.policeOsago) && Objects.equals(policeKasko, osagoInfo.policeKasko);
    }

    @Override
    public int hashCode() {
        return Objects.hash(num, index, creditorName, creditorAddress, policeOsago, policeKasko);
    }
}
