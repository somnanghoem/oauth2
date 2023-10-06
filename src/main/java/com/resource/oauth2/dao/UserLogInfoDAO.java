package com.resource.oauth2.dao;

import com.resource.oauth2.dto.userlog.UserErrorLogDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserLogInfoDAO {

    long registerUserErrorLogInfo(UserErrorLogDTO param ) throws Exception;
}
