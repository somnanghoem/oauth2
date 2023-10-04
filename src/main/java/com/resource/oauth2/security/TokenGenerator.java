package com.resource.oauth2.security;

import com.resource.oauth2.dto.token.GenerateUserTokenRequst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Component
public class TokenGenerator {
    @Autowired
    JwtEncoder  accessTokenEncoder;

    @Autowired
    @Qualifier("jwtRefreshTokenEncoder")
    JwtEncoder refreshTokenEncoder;

    public String generateAccessToken( GenerateUserTokenRequst generateUserTokenRequst)  {
        Instant now = Instant.now();
        JwtClaimsSet claimsSet = JwtClaimsSet.builder()
                .issuer(generateUserTokenRequst.getUserName())
                .issuedAt(now)
                .expiresAt(now.plus(5, ChronoUnit.MINUTES ) )
                .subject(generateUserTokenRequst.getUserName())
                .build();
        return  accessTokenEncoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();
    }

    public String generateRefreshToken( GenerateUserTokenRequst generateUserTokenRequst) {
        Instant now = Instant.now();
        JwtClaimsSet claimsSet = JwtClaimsSet.builder()
                .issuer( generateUserTokenRequst.getUserName() )
                .issuedAt(now)
                .expiresAt(now.plus(5, ChronoUnit.MINUTES ) )
                .subject( generateUserTokenRequst.getUserName() )
                .build();
        return  accessTokenEncoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();
    }

}
