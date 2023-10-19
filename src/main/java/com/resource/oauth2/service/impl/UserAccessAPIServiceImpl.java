package com.resource.oauth2.service.impl;

import com.resource.oauth2.dao.UserAccessAPIDAO;
import com.resource.oauth2.dto.UserAccessAPIDTO;
import com.resource.oauth2.service.UserAccessAPIService;
import com.resource.oauth2.type.ResponseResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAccessAPIServiceImpl implements UserAccessAPIService {

    @Autowired
    UserAccessAPIDAO userAccessAPIDAO;
    @Override
    public void validateUserAccessAPI(String userName, String uri) throws Exception {
        try {

            List<UserAccessAPIDTO> listAccessAPI = userAccessAPIDAO.retrieveListUserAccessAPIByUserName( userName );
            String accessYN = "N";
            if ( listAccessAPI.size() > 0 ) {
                for ( UserAccessAPIDTO userAccessAPI: listAccessAPI ) {
                    if ( userAccessAPI.getuRI().equals( uri ) ) {
                        accessYN = userAccessAPI.getAccessYN();
                        break;
                    }
                }
            } else {
                throw new Exception(ResponseResultMessage.DUN_ALLOW_ACCESS_API.getValue() );
            }
            if ( "N".equals( accessYN ) ) {
                throw new Exception(ResponseResultMessage.DUN_ALLOW_ACCESS_API.getValue() );
            }
        } catch ( Exception e ){
            e.printStackTrace();
            throw e;
        }
    }
}
