package com.resource.oauth2.dto;

public class ErrorMessageDTO {

    private String errorCode = "";
    private String errorLanguage = "";
    private String errorDescription = "";
    private String createBy= "";
    private String createDate= "";
    private String CreateTime= "";
    private String updateDate= "";
    private String updateTime= "";
    private String updateBy ="";

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorLanguage() {
        return errorLanguage;
    }

    public void setErrorLanguage(String errorLanguage) {
        this.errorLanguage = errorLanguage;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
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
        return "ErrorMessageDTO{" +
                "errorCode='" + errorCode + '\'' +
                ", errorLanguage='" + errorLanguage + '\'' +
                ", errorDescription='" + errorDescription + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createDate='" + createDate + '\'' +
                ", CreateTime='" + CreateTime + '\'' +
                ", updateDate='" + updateDate + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", updateBy='" + updateBy + '\'' +
                '}';
    }
}
