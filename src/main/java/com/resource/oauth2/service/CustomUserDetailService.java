package com.resource.oauth2.service;

import com.resource.oauth2.dao.UserInfoDAO;
import com.resource.oauth2.dto.UserInfoDTO;
import com.resource.oauth2.security.User;
import com.resource.oauth2.type.ResponseResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
               throw new UsernameNotFoundException(ResponseResultMessage.USER_NOT_FOUND.getValue());
           }
           return new User( userInfo.getUserName(), userInfo.getUserPassword());
       } catch (Exception e ) {
            throw new UsernameNotFoundException(ResponseResultMessage.USER_NOT_FOUND.getValue());
       }
    }
}
