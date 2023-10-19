package com.resource.oauth2.service.impl;

import com.resource.oauth2.dao.UserLogInfoDAO;
import com.resource.oauth2.dto.UserErrorLogDTO;
import com.resource.oauth2.service.UserLogService;
import com.resource.oauth2.util.DateUtil;
import com.resource.oauth2.util.RequestHeader;
import com.resource.oauth2.util.ResponseHeader;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLogServiceImpl implements UserLogService {

    @Autowired
    UserLogInfoDAO  userLogInfoDAO;
    @Override
    public UserErrorLogDTO registerUserErrorLogInfo(RequestHeader header, ResponseHeader responseHeader, Exception e) {

        UserErrorLogDTO registerUserLogInfo = new UserErrorLogDTO();
        try {
            if (StringUtils.isBlank(header.getUserName()) || StringUtils.isEmpty(header.getUserName())){
                registerUserLogInfo.setUserName("General".concat(DateUtil.getCurrentFormatDate(DateUtil.TIME)));
            } else {
                registerUserLogInfo.setUserName(header.getUserName());
            }
            if (StringUtils.isBlank(header.getUserType()) || StringUtils.isEmpty(header.getUserType())){
                registerUserLogInfo.setUserType("GN");
            } else {
                registerUserLogInfo.setUserType(header.getUserType());
            }
            registerUserLogInfo.setRegisterDate(DateUtil.getCurrentFormatDate(DateUtil.DATE));
            registerUserLogInfo.setRegisterTime(DateUtil.getCurrentFormatDate(DateUtil.TIME));
            registerUserLogInfo.setUrl("");
            registerUserLogInfo.setIpAddress("");
            registerUserLogInfo.setDeviceName("");
            registerUserLogInfo.setErrorCode(responseHeader.getResultCode());
            registerUserLogInfo.setErrorDescription(responseHeader.getResultMessage());
            registerUserLogInfo.setErrorCause(ExceptionUtils.getStackTrace(e));
            userLogInfoDAO.registerUserErrorLogInfo(registerUserLogInfo);

        } catch ( Exception ex ) {
            ex.printStackTrace();
        }
        return registerUserLogInfo;
    }
}
