package com.resource.oauth2.service.impl;

import com.resource.oauth2.dto.user.RegisterUserInfoRequest;
import com.resource.oauth2.dto.user.UserInfoDTO;
import com.resource.oauth2.service.UserInfoService;
import com.resource.oauth2.type.language.ResponseResultMessageEnglish;
import com.resource.oauth2.type.UserTypeCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Override
    public UserInfoDTO registerUserInfo(RegisterUserInfoRequest param) throws Exception {

        try {
            // Validate request data
            this.validationRegisterParam(param);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return null;
    }

    private void validationRegisterParam( RegisterUserInfoRequest param ) throws Exception {
        if(StringUtils.isEmpty(param.getUserName()) || StringUtils.isBlank(param.getUserName())) {
            throw new Exception(ResponseResultMessageEnglish.USER_NAME_EMPTY.getValue());
        }else if(StringUtils.isEmpty(param.getUserPassword()) || StringUtils.isBlank(param.getUserPassword())) {
            throw new Exception(ResponseResultMessageEnglish.PASSWORD_EMPTY.getValue());
        }else if(StringUtils.isEmpty(param.getUserType()) || StringUtils.isBlank(param.getUserType())){
            throw new Exception(ResponseResultMessageEnglish.USER_TYPE_EMPTY.getValue());
        }else if ( StringUtils.isEmpty(param.getMasterUserName()) || StringUtils.isBlank(param.getMasterUserName())){
            throw new Exception(ResponseResultMessageEnglish.MASTER_USER_NAME_EMPTY.getValue());
        }
        List<String> userTypes = Arrays.asList(UserTypeCode.MOBILE_USER.getValue(),UserTypeCode.WEB_USER.getValue());
        if (!userTypes.contains(param.getUserType())){
            throw new Exception(ResponseResultMessageEnglish.INVALID_USER_TYPE.getValue());
        }
    }

    @Override
    public UserInfoDTO updateUserInfo(UserInfoDTO param) throws Exception {
        return null;
    }

    @Override
    public UserInfoDTO retrieveUserInfoByUserName(UserInfoDTO param) throws Exception {
        return null;
    }

    @Override
    public UserInfoDTO retrieveUserInfoByUserNameForUpdate(UserInfoDTO param) throws Exception {
        return null;
    }

    @Override
    public List<UserInfoDTO> retrieveListUserInfo(String status) throws Exception {
        return null;
    }
}
