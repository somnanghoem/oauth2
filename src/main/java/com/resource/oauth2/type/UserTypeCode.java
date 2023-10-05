package com.resource.oauth2.type;

public enum UserTypeCode {
    MOBILE_USER( "01", "Mobile User"),
    WEB_USER( "02", "Web User");

    private String value;
    private String description;

    UserTypeCode(String value, String description) {
        this.value = value;
        this.description = description;
    }

    public String getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }
}
