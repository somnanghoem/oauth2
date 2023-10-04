package com.resource.oauth2.type;

import com.resource.oauth2.util.ResponseHeader;

public enum ResponseResultMessage {

    SUCCESS ( "0000", "Success"),
    GENERAL_SYSTEM_ERROR( "9999", "General system error"),
    USER_NOT_FOUND( "0002", "User information not found");

    ResponseResultMessage(String value, String description) {
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

    private static ResponseResultMessage getResultMessageInfo( String value ) {
        ResponseResultMessage resultConst = null;
        if ( value != null ) {
            for ( ResponseResultMessage searchConst : values() ) {
                if ( searchConst.getValue().equals( value ) ) {
                    resultConst = searchConst;
                    break;
                }
            }
        }
        return resultConst;
    }

    public static ResponseHeader resultOuputMessage(Exception e ) {
        ResponseHeader header = new ResponseHeader();
        ResponseResultMessage resultMessageInfo = null;
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
