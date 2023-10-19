package com.resource.oauth2.dao;

import com.resource.oauth2.dto.ErrorMessageDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ErrorMessageDAO {

    public ErrorMessageDTO retrieveErrorMessageInfo( ErrorMessageDTO param ) ;
}
