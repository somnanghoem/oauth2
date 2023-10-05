package com.resource.oauth2.controller.token;


import com.resource.oauth2.dto.token.GenerateUserTokenRequst;
import com.resource.oauth2.dto.token.GenerateUserTokenResponse;
import com.resource.oauth2.service.GenerateUserTokenService;
import com.resource.oauth2.type.language.ResponseResultMessage;
import com.resource.oauth2.type.language.ResponseResultMessageEnglish;
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
public class UserTokenManagementController {

    @Autowired
    GenerateUserTokenService generateUserTokenService;

    @PostMapping(value = "")
    public ResponseEntity generateUserToken(@RequestBody RequestData<GenerateUserTokenRequst> requestData ) {

        ResponseHeader header = new ResponseHeader("Y", ResponseResultMessageEnglish.SUCCESS.getValue(), ResponseResultMessageEnglish.SUCCESS.getDescription() );
        GenerateUserTokenResponse body = new GenerateUserTokenResponse();
        try {
            GenerateUserTokenRequst generateUserTokenRequst = requestData.getBody();
            body = generateUserTokenService.generateUserTokenInfo(generateUserTokenRequst);
        } catch ( Exception e ) {
            e.printStackTrace();
            body = new GenerateUserTokenResponse();
            header = ResponseResultMessage.resultLanguageMessage(requestData.getHeader(),e);
        }
        ResponseData<GenerateUserTokenResponse> responseData = new ResponseData<>(header, body);
        return  ResponseEntity.ok( responseData );
    }

}
