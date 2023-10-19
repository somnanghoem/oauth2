package com.resource.oauth2.service;

import com.resource.oauth2.model.user.RegisterUserInfoRequest;
import com.resource.oauth2.dto.UserInfoDTO;

public interface UserInfoService {
    UserInfoDTO registerUserInfo(RegisterUserInfoRequest param ) throws Exception;
    UserInfoDTO updateUserInfo(UserInfoDTO param ) throws Exception;
}
