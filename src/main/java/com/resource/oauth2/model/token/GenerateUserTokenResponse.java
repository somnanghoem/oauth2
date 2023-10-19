package com.resource.oauth2.model.token;

import java.util.ArrayList;
import java.util.List;

public class GenerateUserTokenResponse {

    private String token = "";
    private String expiredDate = "";
    private String expiredTime = "";
    private String issuedDate = "";
    private String issuedTime = "";

    private List<UserTokenScope> scopes = new ArrayList<>();

    public List<UserTokenScope> getScopes() {
        return scopes;
    }

    public void setScopes(List<UserTokenScope> scopes) {
        this.scopes = scopes;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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
}
