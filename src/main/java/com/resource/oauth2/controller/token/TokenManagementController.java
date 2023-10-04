package com.resource.oauth2.controller.token;


import com.resource.oauth2.dto.token.GenerateUserTokenRequst;
import com.resource.oauth2.dto.token.GenerateUserTokenResponse;
import com.resource.oauth2.security.TokenGenerator;
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
@RequestMapping("/api/v1/token")
public class TokenManagementController {

    @Autowired
    TokenGenerator tokenGenerator;

    @PostMapping(value = "")
    public ResponseEntity generateUserToken(@RequestBody RequestData<GenerateUserTokenRequst> requestData ) {

        ResponseHeader header = new ResponseHeader("Y",ResponseResultMessage.SUCCESS.getValue(), ResponseResultMessage.SUCCESS.getDescription() );
        GenerateUserTokenResponse body = new GenerateUserTokenResponse();
        try {
            GenerateUserTokenRequst generateUserTokenRequst = requestData.getBody();
            String token = tokenGenerator.generateAccessToken(generateUserTokenRequst);
            body.setToken(token);
        } catch ( Exception e ) {
            e.printStackTrace();
            body = new GenerateUserTokenResponse();
            header = ResponseResultMessage.resultOuputMessage(e);
        }
        ResponseData<GenerateUserTokenResponse> responseData = new ResponseData<>(header, body);
        return  ResponseEntity.ok( responseData );
    }

}
