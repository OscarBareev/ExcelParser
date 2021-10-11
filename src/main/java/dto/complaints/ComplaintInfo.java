package dto.complaints;

import java.util.Objects;

public class ComplaintInfo {

    private String guiltyName;
    private String guiltyAddress;
    private String contractNum;
    private String contractDate;
    private String carType;
    private String carNum;
    private String dept;
    private String company;
    private String type;


    public ComplaintInfo(String guiltyName, String guiltyAddress, String contractNum, String contractDate, String carType, String carNum, String dept, String company, String type) {
        this.guiltyName = guiltyName;
        this.guiltyAddress = guiltyAddress;
        this.contractNum = contractNum;
        this.contractDate = contractDate;
        this.carType = carType;
        this.carNum = carNum;
        this.dept = dept;
        this.company = company;
        this.type = type;
    }

    public String getGuiltyName() {
        return guiltyName;
    }

    public void setGuiltyName(String guiltyName) {
        this.guiltyName = guiltyName;
    }

    public String getGuiltyAddress() {
        return guiltyAddress;
    }

    public void setGuiltyAddress(String guiltyAddress) {
        this.guiltyAddress = guiltyAddress;
    }

    public String getContractNum() {
        return contractNum;
    }

    public void setContractNum(String contractNum) {
        this.contractNum = contractNum;
    }

    public String getContractDate() {
        return contractDate;
    }

    public void setContractDate(String contractDate) {
        this.contractDate = contractDate;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ComplaintInfo)) return false;
        ComplaintInfo that = (ComplaintInfo) o;
        return Objects.equals(getGuiltyName(), that.getGuiltyName()) && Objects.equals(getGuiltyAddress(), that.getGuiltyAddress()) && Objects.equals(getContractNum(), that.getContractNum()) && Objects.equals(getContractDate(), that.getContractDate()) && Objects.equals(getCarType(), that.getCarType()) && Objects.equals(getCarNum(), that.getCarNum()) && Objects.equals(getDept(), that.getDept()) && Objects.equals(getCompany(), that.getCompany()) && Objects.equals(getType(), that.getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGuiltyName(), getGuiltyAddress(), getContractNum(), getContractDate(), getCarType(), getCarNum(), getDept(), getCompany(), getType());
    }
}
