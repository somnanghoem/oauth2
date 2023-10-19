package com.resource.oauth2.dao;

import com.resource.oauth2.dto.UserInfoDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface UserInfoDAO {

    long registerUserInfo(UserInfoDTO param ) throws Exception;
    long updateUserInfo(UserInfoDTO param ) throws Exception;
    UserInfoDTO retrieveUserInfo( UserInfoDTO param ) throws  Exception;
    UserInfoDTO retrieveUserInfoByUserNameForUpdate( UserInfoDTO param ) throws Exception;
    List<UserInfoDTO> retrieveListUserInfo( String status ) throws Exception;
}
