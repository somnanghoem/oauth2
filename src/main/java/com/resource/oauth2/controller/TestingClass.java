package com.resource.oauth2.controller;

import com.resource.oauth2.service.ResponseResultMessageService;
import com.resource.oauth2.type.language.ResponseResultMessageEnglish;
import com.resource.oauth2.util.RequestHeader;
import com.resource.oauth2.util.ResponseHeader;
import com.resource.oauth2.util.ThreadLocalUtil;

public class TestingClass {



 public static void main(String[]args) throws  Exception {

  ResponseResultMessageService service = new ResponseResultMessageService();
  ThreadLocalUtil.setErrorName("${uri}");
  ThreadLocalUtil.setErrorMessage("/api/vi/get_token");
  RequestHeader header = new RequestHeader();
  header.setLanguage("en");
 try {
    if ( "1".equals("1")) {
      throw new Exception(ResponseResultMessageEnglish.DUN_ALLOW_ACCESS_API.getValue() );
    }
 } catch ( Exception e ){
   System.out.println(service.resultLanguageMessage( header, e ).toString());
 }



  }
}
