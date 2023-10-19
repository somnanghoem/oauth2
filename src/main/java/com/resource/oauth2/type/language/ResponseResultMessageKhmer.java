package com.resource.oauth2.type.language;

import com.resource.oauth2.util.ResponseHeader;

public enum ResponseResultMessageKhmer {
    SUCCESS ( "0000", "ជោគជ័យ"),
    GENERAL_SYSTEM_ERROR( "9999", "កំហុសប្រព័ន្ធទូទៅ"),
    USER_NOT_FOUND( "0002", "រកមិនឃើញព័ត៌មានអ្នកប្រើប្រាស់ទេ។"),
    USER_NAME_EMPTY( "0003", "ឈ្មោះអ្នកប្រើប្រាស់មិនអាចទទេ។"),
    PASSWORD_EMPTY( "0004", "ពាក្យសម្ងាត់មិនអាចទទេ។"),
    INVALID_PASSWORD( "0005", "ពាក្យសម្ងាត់មិនត្រឹមត្រូវ"),
    REGISTER_TOKEN_ERROR( "0006", "កំហុសក្នុងការចុះឈ្មោះអ្នកប្រើប្រាស់ព័ត៌មាន"),
    TOKEN_NOT_FOUND( "0007", "រកមិនឃើញព័ត៌មានសញ្ញាសម្ងាត់ទេ។"),
    TOKEN_EXPIRED( "0008", "Token បានផុតកំណត់ហើយ"),
    USER_TYPE_EMPTY ( "0009", "ប្រភេទអ្នកប្រើប្រាស់មិនអាចទទេបានទេ។"),
    INVALID_USER_TYPE( "0010", "ប្រភេទអ្នកប្រើមិនត្រឹមត្រូវ។"),
    MASTER_USER_NAME_EMPTY ( "0011", "ឈ្មោះអ្នកប្រើមេមិនអាចទទេបានទេ។"),
    USER_NAME_ALREADY_EXISTING ( "0012", "ឈ្មោះអ្នកប្រើប្រាស់មានរួចហើយ សូមជ្រើសរើសឈ្មោះអ្នកប្រើប្រាស់ផ្សេងទៀត។"),
    DUN_ALLOW_ACCESS_API ( "0013", "User doesn't allow to access this api ${uri}");

    ResponseResultMessageKhmer(String value, String description) {
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

    private static ResponseResultMessageKhmer getResultMessageInfo(String value ) {
        ResponseResultMessageKhmer resultConst = null;
        if ( value != null ) {
            for ( ResponseResultMessageKhmer searchConst : values() ) {
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
        ResponseResultMessageKhmer resultMessageInfo = null;
        if ( e != null ) {
            if ( e.getMessage().length() > 4 ) {
                resultMessageInfo = getResultMessageInfo( "9999" );
            } else {
                resultMessageInfo = getResultMessageInfo( e.getMessage() );
            }
        } else {
            resultMessageInfo = getResultMessageInfo( "9999" );
        }

        header.setSuccessYN("N");
        header.setResultCode(resultMessageInfo.getValue());
        header.setResultMessage(resultMessageInfo.getDescription());
        return  header;
    }
}
