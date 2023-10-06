package com.resource.oauth2.service;

import com.resource.oauth2.dto.userlog.UserErrorLogDTO;
import com.resource.oauth2.util.RequestHeader;
import com.resource.oauth2.util.ResponseHeader;

public interface UserLogService {
    UserErrorLogDTO registerUserErrorLogInfo(RequestHeader header, ResponseHeader responseHeader, Exception e) ;
}
