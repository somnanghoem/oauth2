package com.resource.oauth2.service.impl;

import com.resource.oauth2.dao.UserInfoDAO;
import com.resource.oauth2.dao.UserTokenInfoDAO;
import com.resource.oauth2.dto.token.UserTokenInfoDTO;
import com.resource.oauth2.dto.token.GenerateUserTokenRequst;
import com.resource.oauth2.dto.token.GenerateUserTokenResponse;
import com.resource.oauth2.dto.user.UserInfoDTO;
import com.resource.oauth2.security.PropertiesPlaceholderConfiguration;
import com.resource.oauth2.security.TokenGenerator;
import com.resource.oauth2.service.GenerateUserTokenService;
import com.resource.oauth2.type.language.ResponseResultMessageEnglish;
import com.resource.oauth2.util.DateUtil;
import com.resource.oauth2.util.encryption.Sha256Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class GenerateUserTokenServiceImpl implements GenerateUserTokenService {
    @Autowired
    UserInfoDAO UserInfoDAO;
    @Autowired
    TokenGenerator tokenGenerator;
    @Autowired
    UserTokenInfoDAO userTokenInfoDAO;
    @Autowired
    PropertiesPlaceholderConfiguration config;
    @Autowired
    PlatformTransactionManager platformTransactionManager;
    @Override
    public GenerateUserTokenResponse generateUserTokenInfo(GenerateUserTokenRequst requestParam) throws Exception {
        // Open New Transaction
        TransactionStatus transactionStatus = platformTransactionManager.getTransaction( new DefaultTransactionAttribute( TransactionDefinition.PROPAGATION_REQUIRES_NEW ) );
        try {
            // validate request data
            this.validationRequestData(requestParam);
            // Generate Token Info
            GenerateUserTokenResponse tokenResponse =processRegisterUpdateUserTokenInfo(requestParam);
            // Commit Transaction
            platformTransactionManager.commit(transactionStatus);
           return tokenResponse;
        } catch ( Exception e ) {
            // Rollback transaction
            platformTransactionManager.rollback(transactionStatus);
            throw e;
        }
    }

    private void validationRequestData( GenerateUserTokenRequst requestParam ) throws Exception {
        if (StringUtils.isBlank(requestParam.getUserName()) || StringUtils.isEmpty(requestParam.getUserName())){
            throw new Exception(ResponseResultMessageEnglish.USER_NAME_EMPTY.getValue());
        }else if (StringUtils.isBlank(requestParam.getPassword()) || StringUtils.isEmpty(requestParam.getPassword())){
            throw new Exception(ResponseResultMessageEnglish.PASSWORD_EMPTY.getValue());
        }
        // Validate User Information
        UserInfoDTO userParam = new UserInfoDTO();
        userParam.setUserName(requestParam.getUserName());
        UserInfoDTO userInfo = UserInfoDAO.retrieveUserInfo(userParam);
        if ( userInfo == null ) {
            throw new Exception( ResponseResultMessageEnglish.USER_NOT_FOUND.getValue() );
        } else {
            String encryptUserPassword = Sha256Util.encrypt(requestParam.getPassword(), config.getSha256Secret().concat(requestParam.getUserName()));
            if ( !encryptUserPassword.equals(userInfo.getUserPassword())){
                throw new Exception( ResponseResultMessageEnglish.INVALID_PASSWORD.getValue() );
            }
        }
    }

    private GenerateUserTokenResponse processRegisterUpdateUserTokenInfo( GenerateUserTokenRequst requestParam ) throws Exception {

        try {
            GenerateUserTokenResponse tokenResponse = new GenerateUserTokenResponse();
            UserTokenInfoDTO registerUserInfo = new UserTokenInfoDTO();
            // Retrieve User Token Info
            UserTokenInfoDTO userParam = new UserTokenInfoDTO();
            userParam.setUserName(requestParam.getUserName());
            UserTokenInfoDTO userTokenInfo = userTokenInfoDAO.retrieveUserTokenInfo(userParam);

            // Process Register User Token Info
            if ( userTokenInfo == null ) {
                // Generate Token Info
                tokenResponse = tokenGenerator.generateAccessToken(requestParam);
                registerUserInfo.setUserName( requestParam.getUserName() );
                registerUserInfo.setToken( tokenResponse.getToken() );
                registerUserInfo.setIssuedDate( tokenResponse.getIssuedDate() );
                registerUserInfo.setIssuedTime( tokenResponse.getIssuedTime() );
                registerUserInfo.setExpiredDate( tokenResponse.getExpiredDate() );
                registerUserInfo.setExpiredTime( tokenResponse.getExpiredTime() );
                registerUserInfo.setRemoteIP("");
                registerUserInfo.setStatus("0"); // 0: Normal, 1: Delete
                registerUserInfo.setUserType("01"); // 01: Mobile, 02: Web
                userTokenInfoDAO.registerUserTokenInfo(registerUserInfo);
            }
            // Process Update User Token Info
            else {
                SimpleDateFormat sdDate = new SimpleDateFormat(DateUtil.DATETIME);
                Date expiredDateTime = sdDate.parse( userTokenInfo.getExpiredDate().concat(userTokenInfo.getExpiredTime()) ) ;
                Date currentDateTime = sdDate.parse(DateUtil.getCurrentFormatDate(DateUtil.DATETIME));
                if (( expiredDateTime.compareTo(currentDateTime) <=0)) {
                    tokenResponse = tokenGenerator.generateAccessToken(requestParam);
                    registerUserInfo = userTokenInfo;
                    registerUserInfo.setUserName( requestParam.getUserName() );
                    registerUserInfo.setToken( tokenResponse.getToken() );
                    registerUserInfo.setIssuedDate( tokenResponse.getIssuedDate() );
                    registerUserInfo.setIssuedTime( tokenResponse.getIssuedTime() );
                    registerUserInfo.setExpiredDate( tokenResponse.getExpiredDate() );
                    registerUserInfo.setExpiredTime( tokenResponse.getExpiredTime() );
                    userTokenInfoDAO.updateUserTokenInfo(registerUserInfo);
                } else {
                    tokenResponse.setToken(userTokenInfo.getToken());
                    tokenResponse.setIssuedDate(userTokenInfo.getIssuedDate());
                    tokenResponse.setIssuedTime(userTokenInfo.getIssuedTime());
                    tokenResponse.setExpiredDate(userTokenInfo.getExpiredDate());
                    tokenResponse.setExpiredTime(userTokenInfo.getExpiredTime());
                }
            }
        return  tokenResponse;
        } catch ( Exception e ) {
            e.printStackTrace();
            throw new Exception( ResponseResultMessageEnglish.REGISTER_TOKEN_ERROR.getValue() );
        }
    }

}
