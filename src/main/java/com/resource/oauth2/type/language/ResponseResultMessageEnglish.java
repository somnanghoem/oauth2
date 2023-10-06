package com.resource.oauth2.type.language;

import com.resource.oauth2.util.ResponseHeader;

public enum ResponseResultMessageEnglish {

    SUCCESS ( "0000", "Success"),
    GENERAL_SYSTEM_ERROR( "9999", "General system error"),
    USER_NOT_FOUND( "0002", "User information not found"),
    USER_NAME_EMPTY( "0003", "User name cannot be empty or null"),
    PASSWORD_EMPTY( "0004", "Password cannot be empty or null"),
    INVALID_PASSWORD( "0005", "Invalid password"),
    REGISTER_TOKEN_ERROR( "0006", "Register user token information error"),
    TOKEN_NOT_FOUND( "0007", "Not found token info"),
    TOKEN_EXPIRED( "0008", "Token has been expired"),
    USER_TYPE_EMPTY ( "0009", "User type cannot be empty or expired"),
    INVALID_USER_TYPE( "0010", "Invalid user type"),
    MASTER_USER_NAME_EMPTY ( "0011", "Master user name cannot be empty or null"),
    USER_NAME_ALREADY_EXISTING ( "0012", "Username already exists, please choose other user name");

    ResponseResultMessageEnglish(String value, String description) {
        this.value = value;
        this.description = description;
    }

    private String value;
    private String description;

    public String getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    private static ResponseResultMessageEnglish getResultMessageInfo(String value ) {
        ResponseResultMessageEnglish resultConst = null;
        if ( value != null ) {
            for ( ResponseResultMessageEnglish searchConst : values() ) {
                if ( searchConst.getValue().equals( value ) ) {
                    resultConst = searchConst;
                    break;
                }
            }
        }
        return resultConst;
    }

    public static ResponseHeader resultOutputMessage(Exception e ) {
        ResponseHeader header = new ResponseHeader();
        ResponseResultMessageEnglish resultMessageInfo = null;
        if ( e.getMessage().length() > 4 ) {
            resultMessageInfo = getResultMessageInfo( "9999" );
        } else {
            resultMessageInfo = getResultMessageInfo( e.getMessage() );
        }
        header.setSuccessYN("N");
        header.setResultCode(resultMessageInfo.getValue());
        header.setResultMessage(resultMessageInfo.getDescription());
        return  header;
    }

}
