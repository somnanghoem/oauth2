package com.resource.oauth2.service;

import com.resource.oauth2.dto.UserErrorLogDTO;
import com.resource.oauth2.util.RequestHeader;
import com.resource.oauth2.util.ResponseHeader;

public interface UserLogService {
    UserErrorLogDTO registerUserErrorLogInfo(RequestHeader header, ResponseHeader responseHeader, Exception e) ;
}
