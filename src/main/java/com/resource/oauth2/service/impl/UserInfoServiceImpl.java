package com.resource.oauth2.service.impl;

import com.resource.oauth2.dao.UserInfoDAO;
import com.resource.oauth2.dto.user.RegisterUserInfoRequest;
import com.resource.oauth2.dto.user.UserInfoDTO;
import com.resource.oauth2.security.PropertiesPlaceholderConfiguration;
import com.resource.oauth2.service.UserInfoService;
import com.resource.oauth2.type.UserStatusCode;
import com.resource.oauth2.type.language.ResponseResultMessageEnglish;
import com.resource.oauth2.type.UserTypeCode;
import com.resource.oauth2.util.DateUtil;
import com.resource.oauth2.util.encryption.Sha256Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    UserInfoDAO userInfoDAO;
    @Autowired
    PropertiesPlaceholderConfiguration config;

    @Override
    public UserInfoDTO registerUserInfo(RegisterUserInfoRequest param) throws Exception {
        try {
            // Validate request data
            this.validationRegisterParam(param);
            // Process Register User Info
            String encryptUserPassword = Sha256Util.encrypt(param.getUserPassword(), config.getSha256Secret().concat(param.getUserName()));
            UserInfoDTO registerUserInfo = new UserInfoDTO();
            registerUserInfo.setUserName(param.getUserName());
            registerUserInfo.setUserType(UserTypeCode.MOBILE_USER.getValue());
            registerUserInfo.setUserStatus(UserStatusCode.NORMAL.getValue());
            registerUserInfo.setUserPassword(encryptUserPassword);
            registerUserInfo.setMasterUserName(param.getMasterUserName());
            registerUserInfo.setCreateBy(param.getMasterUserName());
            registerUserInfo.setCreateDate(DateUtil.getCurrentFormatDate(DateUtil.DATE));
            registerUserInfo.setCreateTime(DateUtil.getCurrentFormatDate(DateUtil.TIME));
            registerUserInfo.setUpdateDate(DateUtil.getCurrentFormatDate(DateUtil.DATE));
            registerUserInfo.setUpdateTime(DateUtil.getCurrentFormatDate(DateUtil.TIME));
            registerUserInfo.setUpdateBy(param.getMasterUserName());
            userInfoDAO.registerUserInfo(registerUserInfo);
            return registerUserInfo;
        } catch (Exception e) {
            throw e;
        }
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
        // Check If User Information Already Existing
        UserInfoDTO userInfoParam = new UserInfoDTO();
        userInfoParam.setUserName(param.getUserName());
        UserInfoDTO userInfo = userInfoDAO.retrieveUserInfo(userInfoParam);
        if( userInfo != null ) {
            throw new Exception( ResponseResultMessageEnglish.USER_NAME_ALREADY_EXISTING.getValue() );
        }
    }

    @Override
    public UserInfoDTO updateUserInfo(UserInfoDTO param) throws Exception {
        return null;
    }

}
