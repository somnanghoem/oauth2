package com.resource.oauth2.security;

import com.resource.oauth2.service.CustomUserDetailService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    @Autowired
    CustomUserDetailService customUserDetailService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String requestTokenHeader = request.getHeader("Authorization");
        String userName = "";
        String token = "";
        if ( requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ") ) {
            token = requestTokenHeader.substring(7);
            userName = this.getUserName(token);
            System.out.println(userName);
        }

        //Once we get the token validate it.
        if ( StringUtils.isNoneEmpty( userName ) && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = this.customUserDetailService.loadUserByUsername( userName );
            // if token is valid configure Spring Security to manually set authentication
           /* if (jwtTokenUtil.validateToken( jwtToken, userDetails)) {

            }*/
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
        filterChain.doFilter(request, response);
    }

    private String getUserName( String token ) {
        String[] chunks = token.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(chunks[1]));
        JSONObject json = new JSONObject(payload);
        return json.getString("iss");

    }
}
