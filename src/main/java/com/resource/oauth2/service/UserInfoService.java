package com.resource.oauth2.service;

import com.resource.oauth2.dto.user.RegisterUserInfoRequest;
import com.resource.oauth2.dto.user.UserInfoDTO;

import java.util.List;

public interface UserInfoService {
    UserInfoDTO registerUserInfo(RegisterUserInfoRequest param ) throws Exception;
    UserInfoDTO updateUserInfo(UserInfoDTO param ) throws Exception;
    UserInfoDTO retrieveUserInfoByUserName( UserInfoDTO param ) throws  Exception;
    UserInfoDTO retrieveUserInfoByUserNameForUpdate( UserInfoDTO param ) throws Exception;
    List<UserInfoDTO> retrieveListUserInfo(String status ) throws Exception;
}
