package com.resource.oauth2.service;

import com.resource.oauth2.type.language.ResponseResultMessageEnglish;
import com.resource.oauth2.type.language.ResponseResultMessageKhmer;
import com.resource.oauth2.util.RequestHeader;
import com.resource.oauth2.util.ResponseHeader;
import com.resource.oauth2.util.ThreadLocalUtil;
import org.apache.commons.lang3.StringUtils;
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
        // Replace Error Parameter
        try {
           String errorParam =  ThreadLocalUtil.getErrorMessage();
           String errorName = ThreadLocalUtil.getErrorName();
           if ( ( StringUtils.isNoneBlank( errorParam) || StringUtils.isNoneEmpty( errorParam ) )
                 && ( StringUtils.isNoneBlank( errorName) || StringUtils.isNoneEmpty( errorName ) ) ) {
               String resultMessage = responseHeader.getResultMessage();
               if ( resultMessage.contains( errorName ) ) {
                   resultMessage = resultMessage.replace( errorName, errorParam );
               }
               // Remove thread local data
               ThreadLocalUtil.removeThreadLocalData( ThreadLocalUtil.ERROR_NAME);
               ThreadLocalUtil.removeThreadLocalData( ThreadLocalUtil.ERROR_MESSAGE);
               responseHeader.setResultMessage(resultMessage);
           }
        } catch ( Exception ex ) {
            // DO NOTHING
            e.printStackTrace();
        }
        // Register Error Log
       // userLogService.registerUserErrorLogInfo(header,responseHeader,e);
        return  responseHeader;
    }
}
