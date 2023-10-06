package com.resource.oauth2.dto.token;

public class UserTokenInfoDTO {

    private String userName= "";
    private String token= "";
    private String issuedDate= "";
    private String issuedTime= "";
    private String expiredDate= "";
    private String expiredTime= "";
    private String remoteIP= "";
    private String status= "";
    private String userType;

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(String issuedDate) {
        this.issuedDate = issuedDate;
    }

    public String getIssuedTime() {
        return issuedTime;
    }

    public void setIssuedTime(String issuedTime) {
        this.issuedTime = issuedTime;
    }

    public String getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(String expiredDate) {
        this.expiredDate = expiredDate;
    }

    public String getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(String expiredTime) {
        this.expiredTime = expiredTime;
    }

    public String getRemoteIP() {
        return remoteIP;
    }

    public void setRemoteIP(String remoteIP) {
        this.remoteIP = remoteIP;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UserTokenInfoDTO{" +
                "userName='" + userName + '\'' +
                ", token='" + token + '\'' +
                ", issuedDate='" + issuedDate + '\'' +
                ", issuedTime='" + issuedTime + '\'' +
                ", expiredDate='" + expiredDate + '\'' +
                ", expiredTime='" + expiredTime + '\'' +
                ", remoteIP='" + remoteIP + '\'' +
                ", status='" + status + '\'' +
                ", userType='" + userType + '\'' +
                '}';
    }
}
