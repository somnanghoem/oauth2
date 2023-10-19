package com.resource.oauth2.controller;

import com.resource.oauth2.model.user.RegisterUserInfoRequest;
import com.resource.oauth2.model.user.RegisterUserInfoResponse;
import com.resource.oauth2.dto.UserInfoDTO;
import com.resource.oauth2.service.UserInfoService;
import com.resource.oauth2.service.ResponseResultMessageService;
import com.resource.oauth2.type.ResponseResultMessage;
import com.resource.oauth2.util.RequestData;
import com.resource.oauth2.util.ResponseData;
import com.resource.oauth2.util.ResponseHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/user")
public class UserControllerManagement {

    @Autowired
    UserInfoService userInfoService;
    @Autowired
    ResponseResultMessageService responseResultMessageService;
    @PostMapping("/register")
    public ResponseEntity<ResponseData> registerUserInformation(@RequestBody RequestData<RegisterUserInfoRequest> requestData ) {

        RegisterUserInfoResponse body = new RegisterUserInfoResponse();
        ResponseHeader header = new ResponseHeader("Y", ResponseResultMessage.SUCCESS.getValue(), ResponseResultMessage.SUCCESS.getDescription() );
        try {
            UserInfoDTO userInfo = userInfoService.registerUserInfo(requestData.getBody());
            body.setSuccessYN("Y");
            body.setUserName(userInfo.getUserName());
        } catch ( Exception e ) {
            e.printStackTrace();
            body = new RegisterUserInfoResponse();
            body.setSuccessYN("N");
            body.setUserName("");
            header = responseResultMessageService.resultLanguageMessage(requestData.getHeader(),e);
        }
        ResponseData<RegisterUserInfoResponse> responseData = new ResponseData<>(header, body);
        return  ResponseEntity.ok( responseData );
    }
}
