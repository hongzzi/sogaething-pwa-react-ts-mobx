//package com.ssafy.market.domain.user.security.oauth2.user;
//
////import com.project.sogaething.login.domain.AuthProvider;
////import com.project.sogaething.login.exception.OAuth2AuthenticationProcessingException;
//
//import com.ssafy.market.domain.user.domain.AuthProvider;
//import com.ssafy.market.domain.user.exception.OAuth2AuthenticationProcessingException;
//
//import java.util.Map;
//
//public class OAuth2UserInfoFactory {
//
//    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
//        if(registrationId.equalsIgnoreCase(AuthProvider.google.toString())) {
//            return new GoogleOAuth2UserInfo(attributes);
//        } else if (registrationId.equalsIgnoreCase(AuthProvider.naver.toString())) {
//            return new naverOAuth2UserInfo(attributes);
//        } else if (registrationId.equalsIgnoreCase(AuthProvider.kakao.toString())) {
//            return new kakaoOAuth2UserInfo(attributes);
//        } else {
//            throw new OAuth2AuthenticationProcessingException("Sorry! Login with " + registrationId + " is not supported yet.");
//        }
//    }
//}
