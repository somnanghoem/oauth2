package com.resource.oauth2.type;

public enum UserStatusCode {

    NORMAL ("00", "Normal"),
    DELETED("01","Locked"),
    LOCKED("99", "Deleted");

    private String value;
    private String description;

    UserStatusCode(String value, String description) {
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
