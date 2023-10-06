package com.resource.oauth2.dto.token;

public class UserTokenScope {

    private String scropeID = "";
    private String scropeName = "";

    public String getScropeID() {
        return scropeID;
    }

    public void setScropeID(String scropeID) {
        this.scropeID = scropeID;
    }

    public String getScropeName() {
        return scropeName;
    }

    public void setScropeName(String scropeName) {
        this.scropeName = scropeName;
    }
}
