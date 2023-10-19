package com.resource.oauth2.dao;

import com.resource.oauth2.dto.accessapi.UserAccessAPIDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserAccessAPIDAO {
    List<UserAccessAPIDTO>  retrieveListUserAccessAPIByUserName( String userName );
}
