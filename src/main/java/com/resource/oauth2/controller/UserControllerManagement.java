package com.resource.oauth2.controller;

import com.resource.oauth2.dto.user.RegisterUserInfoRequest;
import com.resource.oauth2.dto.user.RegisterUserInfoResponse;
import com.resource.oauth2.type.language.ResponseResultMessage;
import com.resource.oauth2.type.language.ResponseResultMessageEnglish;
import com.resource.oauth2.util.RequestData;
import com.resource.oauth2.util.ResponseData;
import com.resource.oauth2.util.ResponseHeader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/user")
public class UserControllerManagement {

    @PostMapping("/register")
    public ResponseEntity registerUserInformation(@RequestBody RequestData<RegisterUserInfoRequest> requestData ) throws Exception {

        RegisterUserInfoResponse body = new RegisterUserInfoResponse();
        ResponseHeader header = new ResponseHeader("Y", ResponseResultMessageEnglish.SUCCESS.getValue(), ResponseResultMessageEnglish.SUCCESS.getDescription() );
        try {

        } catch ( Exception e ) {
            e.printStackTrace();
            body = new RegisterUserInfoResponse();
            header = ResponseResultMessage.resultLanguageMessage(requestData.getHeader(),e);
        }
        ResponseData<RegisterUserInfoResponse> responseData = new ResponseData<>(header, body);
        return  ResponseEntity.ok( responseData );
    }
}
