package com.resource.oauth2.security;

import com.resource.oauth2.dao.UserTokenInfoDAO;
import com.resource.oauth2.dto.token.UserTokenInfoDTO;
import com.resource.oauth2.service.CustomUserDetailService;
import com.resource.oauth2.service.ResponseResultMessageService;
import com.resource.oauth2.service.UserAccessAPIService;
import com.resource.oauth2.type.language.ResponseResultMessageEnglish;
import com.resource.oauth2.util.*;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    @Autowired
    UserTokenInfoDAO userTokenInfoDAO;
    @Autowired
    UserAccessAPIService userAccessAPIService;
    @Autowired
    CustomUserDetailService customUserDetailService;
    @Autowired
    ResponseResultMessageService responseResultMessageService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String requestTokenHeader = request.getHeader("Authorization");
        String userName = "";
        String token = "";
        if ( requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ") ) {

           try {
               token = requestTokenHeader.substring(7);
               // Validate User Token Info
               UserTokenInfoDTO userTokenParam = new UserTokenInfoDTO();
               userTokenParam.setToken(token);
               UserTokenInfoDTO userTokenInfo = userTokenInfoDAO.retrieveUserTokenInfoByToken(userTokenParam);
               if ( userTokenInfo == null ) {
                throw new Exception( ResponseResultMessageEnglish.TOKEN_NOT_FOUND.getValue());
               } else {
                   userName = userTokenInfo.getUserName();
                   SimpleDateFormat sdDate = new SimpleDateFormat(DateUtil.DATETIME);
                   Date expiredDateTime = sdDate.parse( userTokenInfo.getExpiredDate().concat(userTokenInfo.getExpiredTime()) ) ;
                   Date currentDateTime = sdDate.parse(DateUtil.getCurrentFormatDate(DateUtil.DATETIME));
                   if (( expiredDateTime.compareTo(currentDateTime) <=0)) {
                       throw new Exception( ResponseResultMessageEnglish.TOKEN_EXPIRED.getValue());
                   }
               }
           } catch ( Exception e ) {
               ResponseHeader header = ResponseResultMessageEnglish.resultOutputMessage(e);
               RenderUtil.renderJson( response, new ResponseData<>( header, new Object() ) );
               return;
           }
        }
        // In case authentication request
        if ( StringUtils.isNoneEmpty( userName ) && SecurityContextHolder.getContext().getAuthentication() == null) {
            /********************************************************************************
             *                      Validate User Access API
             *********************************************************************************/
            String baseURI = "";
            try {
                String requestURI = request.getRequestURI();
                String[] slit = requestURI.split("/");
                baseURI = "/" + slit[slit.length - 2].concat("/" + slit[slit.length - 1]);
                userAccessAPIService.validateUserAccessAPI( userName, baseURI );
            } catch ( Exception e ) {
                ThreadLocalUtil.setErrorName("${uri}");
                ThreadLocalUtil.setErrorMessage( baseURI );
                ResponseHeader header = responseResultMessageService.resultLanguageMessage( new RequestHeader(), e );
                RenderUtil.renderJson( response, new ResponseData<>( header, new Object() ) );
                return;
            }
            UserDetails userDetails = this.customUserDetailService.loadUserByUsername( userName );
            SecurityContext context = SecurityContextHolder.createEmptyContext();
            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
            context.setAuthentication(authentication);
            SecurityContextHolder.setContext(context);
            /********************************************************************************
            * Do not use SecurityContextHolder.getContext().setAuthentication(authentication)
            * to avoid race conditions across multiple threads
            *********************************************************************************/
        }

        filterChain.doFilter(request, response);
    }

    @Bean
    public FilterRegistrationBean<JwtTokenFilter> tenantFilterRegistration(JwtTokenFilter filter) {
        //  avoid the duplicate invocation
        FilterRegistrationBean<JwtTokenFilter> registration = new FilterRegistrationBean<>(filter);
        registration.setEnabled(false);
        return registration;
    }
}
