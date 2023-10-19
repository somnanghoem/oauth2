package com.resource.oauth2.model.user;

public class RegisterUserInfoResponse {


    private String successYN = "";
    private String userName = "";

    public String getSuccessYN() {
        return successYN;
    }

    public void setSuccessYN(String successYN) {
        this.successYN = successYN;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "RegisterUserInfoResponse{" +
                "successYN='" + successYN + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
