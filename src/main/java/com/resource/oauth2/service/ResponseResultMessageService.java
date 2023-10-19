package com.resource.oauth2.service;

import com.resource.oauth2.dao.ErrorMessageDAO;
import com.resource.oauth2.dto.ErrorMessageDTO;
import com.resource.oauth2.type.ResponseResultMessage;
import com.resource.oauth2.util.RequestHeader;
import com.resource.oauth2.util.ResponseHeader;
import com.resource.oauth2.util.ThreadLocalUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResponseResultMessageService {

    @Autowired
    ErrorMessageDAO errorMessageDAO;
    @Autowired
    UserLogService userLogService;
    public ResponseHeader resultLanguageMessage(RequestHeader header, Exception e ) {

        ResponseHeader responseHeader = ResponseResultMessage.resultOutputMessage(e);
        ErrorMessageDTO param = new ErrorMessageDTO();
        param.setErrorCode( responseHeader.getResultCode() );
        if ( ( header !=null )
                && ( "kh".equals( header.getLanguage()) ) ) {
            param.setErrorLanguage("kh");
        } else {
            param.setErrorLanguage("en");
        }
        ErrorMessageDTO errorMessageInfo = errorMessageDAO.retrieveErrorMessageInfo( param );
        if ( errorMessageInfo !=  null ) {
            responseHeader.setSuccessYN("N");
            responseHeader.setResultCode( errorMessageInfo.getErrorCode() );
            responseHeader.setResultMessage( errorMessageInfo.getErrorDescription() );
        }

        /************************************************
         * Replace Error Parameter in case has parameter
         ************************************************/
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
        userLogService.registerUserErrorLogInfo(header,responseHeader,e);
        return  responseHeader;
    }
}
