package com.resource.oauth2.type.language;

import com.resource.oauth2.util.RequestHeader;
import com.resource.oauth2.util.ResponseHeader;

public class ResponseResultMessage {
    public static ResponseHeader resultLanguageMessage(RequestHeader header, Exception e ) {
        ResponseHeader responseHeader = new ResponseHeader();
        if ( header !=null ) {
            if ( header.getLanguage().equals("kh")){
                responseHeader = ResponseResultMessageKhmer.resultOutputMessage(e);
            } else {
                responseHeader = ResponseResultMessageEnglish.resultOutputMessage(e);
            }
        } else {
            responseHeader = ResponseResultMessageEnglish.resultOutputMessage(e);
        }
        return  responseHeader;
    }
}
