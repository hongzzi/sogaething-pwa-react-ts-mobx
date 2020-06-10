package com.ssafy.market.domain.user.security;

import com.ssafy.market.domain.user.domain.User;
import com.ssafy.market.domain.user.util.CookieUtils;
import com.ssafy.market.global.config.AppProperties;
import graphql.schema.DataFetchingEnvironment;
import graphql.servlet.GraphQLContext;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service
public class TokenProvider {

    private static final Logger logger = LoggerFactory.getLogger(TokenProvider.class);

    private AppProperties appProperties;

    public TokenProvider(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    public String createToken(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + appProperties.getAuth().getTokenExpirationMsec());

        return Jwts.builder()
                .setSubject(Long.toString(userPrincipal.getId()))
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, appProperties.getAuth().getTokenSecret())
                .compact();
    }

    public String createJwtToken(User user) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + appProperties.getAuth().getTokenExpirationMsec());

        return Jwts.builder()
                .setSubject("Login Token")
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .claim("userId", user.getUserId())
                .claim("userName", user.getName())
                .signWith(SignatureAlgorithm.HS512, appProperties.getAuth().getTokenSecret())
                .compact();
    }

    public Long getUserIdFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(appProperties.getAuth().getTokenSecret())
                .parseClaimsJws(token)
                .getBody();
        return claims.get("userId", Long.class);
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(appProperties.getAuth().getTokenSecret()).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            logger.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            logger.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            logger.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            logger.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            logger.error("JWT claims string is empty.");
        }
        return false;
    }

    public String getTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            System.out.println("토큰 is hear~");
            System.out.println(bearerToken.substring(7, bearerToken.length()));
            return bearerToken.substring(7, bearerToken.length());
        }
        System.out.println("토큰 is null~");
        return null;
    }

    public Long getUserIdFromHeader(DataFetchingEnvironment env){
        GraphQLContext context = env.getContext();
        HttpServletRequest request = context.getHttpServletRequest().get();
        String bearerToken = getTokenFromRequest(request);
        if(bearerToken==null){
            Cookie cookie = CookieUtils.getCookie(request,"token");
//            if(cookie!=null)
//                bearerToken = cookie.getValue();
//            else{
//                System.out.println("쿠키 없음");
//            }

            System.out.println("Is Cookie Null?" + cookie);
            bearerToken = cookie.getValue();

        }
        System.out.println("bearerToken");
        System.out.println(bearerToken);
        Long userId = getUserIdFromToken(bearerToken);
        System.out.println("userId");
        System.out.println(userId);
        return userId;
    }
}
