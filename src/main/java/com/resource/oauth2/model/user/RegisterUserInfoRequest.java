package com.resource.oauth2.model.user;

public class RegisterUserInfoRequest {

    private String userName= "";
    private String userType= "";
    private String userPassword= "";
    private String masterUserName= "";
    private String employeeID= "";

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getMasterUserName() {
        return masterUserName;
    }

    public void setMasterUserName(String masterUserName) {
        this.masterUserName = masterUserName;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    @Override
    public String toString() {
        return "RegisterUserInfoRequest{" +
                "userName='" + userName + '\'' +
                ", userType='" + userType + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", masterUserName='" + masterUserName + '\'' +
                ", employeeID='" + employeeID + '\'' +
                '}';
    }
}
