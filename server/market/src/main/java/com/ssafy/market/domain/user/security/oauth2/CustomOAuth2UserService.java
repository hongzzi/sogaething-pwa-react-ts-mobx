//package com.ssafy.market.domain.user.security.oauth2;
//
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.ssafy.market.domain.user.domain.Role;
//import com.ssafy.market.domain.user.domain.User;
//import com.ssafy.market.domain.user.exception.OAuth2AuthenticationProcessingException;
//import com.ssafy.market.domain.user.repository.UserRepository;
//import com.ssafy.market.domain.user.security.UserPrincipal;
//
//import org.apache.http.HttpResponse;
//import org.apache.http.client.ClientProtocolException;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.impl.client.HttpClientBuilder;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpRequest;
//import org.springframework.security.authentication.InternalAuthenticationServiceException;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
//import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
//import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
//import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
//import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
//import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//import org.springframework.stereotype.Service;
//import org.springframework.util.StringUtils;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletWebRequest;
//import org.springframework.web.context.request.WebRequest;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.Optional;
//
//@Service
//public class CustomOAuth2UserService extends DefaultOAuth2UserService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    HttpServletRequest Request;
//
//    @Override
//    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
//
//        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);
//        String token = Request.getHeader("Authorization");
//        String provider = Request.getHeader("provider");
//        try {
//            if(provider.equals(oAuth2UserRequest.getClientRegistration().getRegistrationId()) && token.equals(oAuth2UserRequest.getAccessToken())){
//                if(provider.equals("naver")){
//                   return naverProfile(token,oAuth2User);
//                }else if(provider.equals("kakao")){
//                    return kakaoProfile(token,oAuth2User);
//                }else if(provider.equals("google")){
//                    return googleProfile(token,oAuth2User);
//                }
//            }
////            return processOAuth2User(oAuth2UserRequest, oAuth2User);
//        } catch (AuthenticationException ex) {
//            throw ex;
//        } catch (Exception ex) {
//            // Throwing an instance of AuthenticationException will trigger the OAuth2AuthenticationFailureHandler
//            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
//        }
//      return null;
//    }
//
//
//    public OAuth2User naverProfile(String token,OAuth2User oAuth2User) {
////        String header = "Bearer " + token; // Bearer 다음에 공백 추가
//        User user = new User();
//        try {
//            String apiURL = "https://openapi.naver.com/v1/nid/me";
//            URL url = new URL(apiURL);
//            HttpURLConnection con = (HttpURLConnection)url.openConnection();
//
//            con.setRequestMethod("POST");
//            con.setRequestProperty("Authorization", token);
//            int responseCode = con.getResponseCode();
//
//            BufferedReader br;
//            if(responseCode==200) { // 정상 호출
//                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
//            } else { // 에러 발생
//                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
//            }
//            String inputLine;
//            StringBuffer response = new StringBuffer();
//            while ((inputLine = br.readLine()) != null) {
//                response.append(inputLine);
//            }
//            br.close();
//            System.out.println(response.toString());
//
////            System.out.println("response");
////            System.out.println(response);
//            JSONParser parser = new JSONParser();
//            Object obj = parser.parse(String.valueOf(response));
//            JSONObject jsonObj = (JSONObject) obj;
//
//            Object obj2 = jsonObj.get("response");
//            JSONObject jsons = (JSONObject) obj2;
//
//            Optional<User> userOptional = userRepository.findByEmail(String.valueOf(jsons.get("email")));
//
//
//            if(!userOptional.isPresent()){
//                user.setEmail(String.valueOf(jsons.get("email")));
//                user.setProvider("naver");
//                user.setProviderId(Long.valueOf(String.valueOf(jsons.get("id"))));
//                user.setName(String.valueOf(jsons.get("name")));
//                user.setImageUrl(String.valueOf(jsons.get("profile_image")));
//                user.setRole(Role.MEMBER);
//                userRepository.save(user);
//            }
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        return UserPrincipal.create(user, oAuth2User.getAttributes());
//
//    }
//
//    public OAuth2User kakaoProfile(String token,OAuth2User oAuth2User) {
////        getKakaoUserInfo(token);
////        String header = "Bearer " + token; // Bearer 다음에 공백 추가
//        User user = new User();
//        try {
//            String apiURL = "https://kapi.kakao.com/v2/user/me";
//            URL url = new URL(apiURL);
////            HttpServletRequest request = HttpServletRequest();
//            HttpURLConnection con = (HttpURLConnection)url.openConnection();
//            con.setRequestMethod("POST");
//            con.setRequestProperty("Authorization", token);
//            int responseCode = con.getResponseCode();
//
//            BufferedReader br;
//            if(responseCode==200) { // 정상 호출
//                br = new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));
//            } else { // 에러 발생
//                br = new BufferedReader(new InputStreamReader(con.getErrorStream(),"UTF-8"));
//            }
//            String inputLine;
//            StringBuffer response = new StringBuffer();
//            while ((inputLine = br.readLine()) != null) {
//                response.append(inputLine);
//            }
//            br.close();
//            // json 형식으로 변환
//            System.out.println("response");
//            System.out.println(response);
//            JSONParser parser = new JSONParser();
//            Object obj = parser.parse(String.valueOf(response));
//            JSONObject jsonObj = (JSONObject) obj;
//
//            Object obj2 = jsonObj.get("properties");
//            JSONObject jsons = (JSONObject) obj2;
//
//            Object obj3 = jsons.get("kakao_account");
//            JSONObject js = (JSONObject) obj3;
//            Object obj4 = js.get("profile");
//            JSONObject jo = (JSONObject) obj4;
//
//            Optional<User> userOptional = userRepository.findByEmail(String.valueOf(jsons.get("email")));
//            if(!userOptional.isPresent()){
//
//            user.setEmail(String.valueOf(jsons.get("email")));
//                user.setProvider("kakao");
////            LinkedHashMap responseData = (LinkedHashMap)userAttributes.get("kakao_account");
//                user.setProviderId(Long.valueOf(String.valueOf(jsonObj.get("id"))));
//                user.setName(String.valueOf(jsons.get("nickname")));
//                user.setImageUrl(String.valueOf(jo.get("profile_image")));
//                user.setRole(Role.MEMBER);
//                userRepository.save(user);
//            }
//
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        return UserPrincipal.create(user, oAuth2User.getAttributes());
//    }
//
//    public OAuth2User googleProfile(String token,OAuth2User oAuth2User) {
////        String header = "Bearer " + token; // Bearer 다음에 공백 추가
//        User user = new User();
////        try {
//            String apiURL = "https://www.googleapis.com/oauth2/v3/userinfo"; // // 아직 안됨
//            final HttpClient client = HttpClientBuilder.create().build();
//
//            ServletWebRequest servletContainer = (ServletWebRequest) RequestContextHolder.getRequestAttributes();
//            HttpServletResponse response = servletContainer.getResponse();
//            response.encodeURL(apiURL);
//            response.setHeader("Authorization",token);
//
//            HttpServletRequest request = servletContainer.getRequest();
//            System.out.println(request.getAttribute("email"));
//
////            request.setAttribute("Authorization",token);
//
////            final HttpPost post = new HttpPost(apiURL);
////            post.addHeader("Authorization", token);
////            post.addHeader("Content-length", String.valueOf(0));
//
////            final HttpResponse response = client.execute(post);
//
////            HttpServletResponse response = (HttpServletResponse) client.execute(post);
////            response.setContentLength(0);
////            int responseCode = response.getStatus();
//////            final int responseCode = response.getStatusLine().getStatusCode();
////
////            System.out.println("\nSending 'POST' request to URL : " + apiURL);
////            System.out.println("Response Code : " + responseCode);
////            System.out.println(response);
////            JsonNode returnNode = null;
////
////            // JSON 형태 반환값 처리
////            ObjectMapper mapper = new ObjectMapper();
////            returnNode = mapper.readTree(response.getEntity().getContent());
////
////            Optional<User> userOptional = userRepository.findByEmail(String.valueOf(returnNode.get("email")));
////            if(!userOptional.isPresent()){
////
////                user.setEmail(String.valueOf(returnNode.get("email")));
////                user.setProvider("google");
////                user.setProviderId(Long.valueOf(String.valueOf(returnNode.get("sub"))));
////                user.setName(String.valueOf(returnNode.get("name")));
////                user.setImageUrl(String.valueOf(returnNode.get("picture")));
////                user.setRole(Role.MEMBER);
////                userRepository.save(user);
////            }
////
////        } catch (ClientProtocolException e) {
////            e.printStackTrace();
////        } catch (IOException e) {
////            e.printStackTrace();
////        } finally {
////            // clear resources
////
////            return UserPrincipal.create(user, oAuth2User.getAttributes());
//            return null;
////            URL url = new URL(apiURL);
////            HttpURLConnection con = (HttpURLConnection)url.openConnection();
////            con.setRequestMethod("POST");
////            con.setRequestProperty("Authorization", token);
////            con.setRequestProperty("Content-Length", "0");
//
////            int responseCode = con.getResponseCode();
//
////            BufferedReader br;
////            if(responseCode==200) { // 정상 호출
////                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
////            } else { // 에러 발생
////                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
////            }
////            String inputLine;
////            StringBuffer response = new StringBuffer();
////            while ((inputLine = br.readLine()) != null) {
////                response.append(inputLine);
////            }
////            br.close();
////            System.out.println(response.toString());
////        } catch (Exception e) {
////            System.out.println(e);
////        }
//    }
//}