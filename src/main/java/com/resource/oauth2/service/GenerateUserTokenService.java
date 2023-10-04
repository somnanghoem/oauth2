package com.resource.oauth2.service;

import com.resource.oauth2.dto.token.GenerateUserTokenRequst;
import com.resource.oauth2.dto.token.GenerateUserTokenResponse;

public interface GenerateUserTokenService {

    public GenerateUserTokenResponse generateUserTokenInfo(GenerateUserTokenRequst requestParam ) throws Exception;
}
