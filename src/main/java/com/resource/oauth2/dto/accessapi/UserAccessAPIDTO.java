package com.resource.oauth2.dto.accessapi;

public class UserAccessAPIDTO {

    private String userID = "";
    private String userType = "";
    private String uRI = "";
    private String uRIDescription = "";
    private String accessYN = "";
    private String createBy= "";
    private String createDate= "";
    private String CreateTime= "";
    private String updateDate= "";
    private String updateTime= "";
    private String updateBy ="";

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getuRI() {
        return uRI;
    }

    public void setuRI(String uRI) {
        this.uRI = uRI;
    }

    public String getuRIDescription() {
        return uRIDescription;
    }

    public void setuRIDescription(String uRIDescription) {
        this.uRIDescription = uRIDescription;
    }

    public String getAccessYN() {
        return accessYN;
    }

    public void setAccessYN(String accessYN) {
        this.accessYN = accessYN;
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
        return "UserAccessAPIDTO{" +
                "userID='" + userID + '\'' +
                ", userType='" + userType + '\'' +
                ", uRI='" + uRI + '\'' +
                ", uRIDescription='" + uRIDescription + '\'' +
                ", accessYN='" + accessYN + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createDate='" + createDate + '\'' +
                ", CreateTime='" + CreateTime + '\'' +
                ", updateDate='" + updateDate + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", updateBy='" + updateBy + '\'' +
                '}';
    }
}
