package com.resource.oauth2.dto;

public class UserErrorLogDTO {
    private String userName = "";
    private String userType = "";
    private String registerDate = "";
    private String registerTime = "";
    private String url = "";
    private String ipAddress = "";
    private String deviceName = "";
    private String errorCode = "";
    private String errorDescription = "";
    private String errorCause = "";

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

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public String getErrorCause() {
        return errorCause;
    }

    public void setErrorCause(String errorCause) {
        this.errorCause = errorCause;
    }

    @Override
    public String toString() {
        return "UserErrorLogDTO{" +
                "userName='" + userName + '\'' +
                ", userType='" + userType + '\'' +
                ", registerDate='" + registerDate + '\'' +
                ", registerTime='" + registerTime + '\'' +
                ", url='" + url + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", errorCode='" + errorCode + '\'' +
                ", errorDescription='" + errorDescription + '\'' +
                ", errorCause='" + errorCause + '\'' +
                '}';
    }
}
