package com.resource.oauth2.service;

import com.resource.oauth2.dao.UserInfoDAO;
import com.resource.oauth2.dto.user.UserInfoDTO;
import com.resource.oauth2.security.User;
import com.resource.oauth2.type.language.ResponseResultMessageEnglish;
import com.resource.oauth2.util.encryption.Sha256Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    UserInfoDAO userInfoDAO;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

       try {
           UserInfoDTO userParam = new UserInfoDTO();
           userParam.setUserName(username);
           UserInfoDTO userInfo = userInfoDAO.retrieveUserInfo(userParam);
           if ( userInfo == null ) {
               throw new UsernameNotFoundException(ResponseResultMessageEnglish.USER_NOT_FOUND.getValue());
           }
           return new User( userInfo.getUserName(), userInfo.getUserPassword());
       } catch (Exception e ) {
            throw new UsernameNotFoundException(ResponseResultMessageEnglish.USER_NOT_FOUND.getValue());
       }
    }
}
