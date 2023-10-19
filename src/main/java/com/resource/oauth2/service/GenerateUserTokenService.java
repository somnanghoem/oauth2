package com.resource.oauth2.service;

import com.resource.oauth2.model.token.GenerateUserTokenRequst;
import com.resource.oauth2.model.token.GenerateUserTokenResponse;

public interface GenerateUserTokenService {

    public GenerateUserTokenResponse generateUserTokenInfo(GenerateUserTokenRequst requestParam ) throws Exception;
}
