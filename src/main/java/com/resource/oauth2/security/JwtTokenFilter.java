package com.resource.oauth2.security;

import com.resource.oauth2.dao.UserTokenInfoDAO;
import com.resource.oauth2.dto.token.UserTokenInfoDTO;
import com.resource.oauth2.service.CustomUserDetailService;
import com.resource.oauth2.type.language.ResponseResultMessageEnglish;
import com.resource.oauth2.util.DateUtil;
import com.resource.oauth2.util.RenderUtil;
import com.resource.oauth2.util.ResponseData;
import com.resource.oauth2.util.ResponseHeader;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
    CustomUserDetailService customUserDetailService;
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

        //Once we get the token validate it.
        if ( StringUtils.isNoneEmpty( userName ) && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = this.customUserDetailService.loadUserByUsername( userName );
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }

        filterChain.doFilter(request, response);
    }

}
