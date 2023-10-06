package com.resource.oauth2.service;

import com.resource.oauth2.type.language.ResponseResultMessageEnglish;
import com.resource.oauth2.type.language.ResponseResultMessageKhmer;
import com.resource.oauth2.util.RequestHeader;
import com.resource.oauth2.util.ResponseHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResponseResultMessageService {

    @Autowired
    UserLogService userLogService;
    public ResponseHeader resultLanguageMessage(RequestHeader header, Exception e ) {
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
        // Register Error Log
        userLogService.registerUserErrorLogInfo(header,responseHeader,e);
        return  responseHeader;
    }
}
