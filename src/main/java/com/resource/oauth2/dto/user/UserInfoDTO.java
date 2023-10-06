package com.resource.oauth2.dto.user;

public class UserInfoDTO {
    
    private String userName= "";
    private String userType= "";
    private String userStatus= "";
    private String userPassword= "";
    private String passwordChangeDate= "";
    private String passwordChangeTime= "";
    private String firstLoginDate= "";
    private String firstLoginTime= "";
    private String lastLoginDate= "";
    private String lastLoginTime= "";
    private String masterUserName= "";
    private String employeeID= "";
    private String createBy= "";
    private String createDate= "";
    private String CreateTime= "";
    private String updateDate= "";
    private String updateTime= "";
    private String updateBy ="";

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

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getPasswordChangeDate() {
        return passwordChangeDate;
    }

    public void setPasswordChangeDate(String passwordChangeDate) {
        this.passwordChangeDate = passwordChangeDate;
    }

    public String getPasswordChangeTime() {
        return passwordChangeTime;
    }

    public void setPasswordChangeTime(String passwordChangeTime) {
        this.passwordChangeTime = passwordChangeTime;
    }

    public String getFirstLoginDate() {
        return firstLoginDate;
    }

    public void setFirstLoginDate(String firstLoginDate) {
        this.firstLoginDate = firstLoginDate;
    }

    public String getFirstLoginTime() {
        return firstLoginTime;
    }

    public void setFirstLoginTime(String firstLoginTime) {
        this.firstLoginTime = firstLoginTime;
    }

    public String getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(String lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
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

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    @Override
    public String toString() {
        return "UserInfoDTO{" +
                "userName='" + userName + '\'' +
                ", userType='" + userType + '\'' +
                ", userStatus='" + userStatus + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", passwordChangeDate='" + passwordChangeDate + '\'' +
                ", passwordChangeTime='" + passwordChangeTime + '\'' +
                ", firstLoginDate='" + firstLoginDate + '\'' +
                ", firstLoginTime='" + firstLoginTime + '\'' +
                ", lastLoginDate='" + lastLoginDate + '\'' +
                ", lastLoginTime='" + lastLoginTime + '\'' +
                ", masterUserName='" + masterUserName + '\'' +
                ", employeeID='" + employeeID + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createDate='" + createDate + '\'' +
                ", CreateTime='" + CreateTime + '\'' +
                ", updateDate='" + updateDate + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", updateBY='" + updateBy + '\'' +
                '}';
    }
}
