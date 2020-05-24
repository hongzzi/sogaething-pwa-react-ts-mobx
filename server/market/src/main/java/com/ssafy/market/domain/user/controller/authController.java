package com.ssafy.market.domain.user.controller;



import com.ssafy.market.domain.user.domain.Role;
import com.ssafy.market.domain.user.domain.User;
import com.ssafy.market.domain.user.repository.UserRepository;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
@RequestMapping("/auth")
public class authController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;



//    @Autowired
//    private TokenProvider tokenProvider;



//    @PostMapping("/login")
//    public ResponseEntity<?> authenticateUser(@Valid HttpServletRequest request) {
//
////        System.out.println(request.getHeader("Authorization"));
//        String token = request.getHeader("Authorization");
////        System.out.println(request.getHeader("provider"));
//        String provider = request.getHeader("provider");
//        if(provider.equals("naver")){
//            naverProfile(token);
//        }else if(provider.equals("kakao")){
//            kakaoProfile(token);
//        }else if(provider.equals("google")){
//            googleProfile(token);
//        }
////        LoginRequest loginRequest
////        Authentication authentication = authenticationManager.authenticate(
////                new UsernamePasswordAuthenticationToken(
////                        loginRequest.getToken(),
////                        loginRequest.getProvider()
////                )
////        );
//
////        SecurityContextHolder.getContext().setAuthentication(authentication);
//
////        String token = tokenProvider.createToken(authentication);
////        return ResponseEntity.ok(new AuthResponse(token));
//        return null;
//    }

//    public void naverProfile(String token) {
////        String header = "Bearer " + token; // Bearer 다음에 공백 추가
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
//                if(responseCode==200) { // 정상 호출
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
//            User user = new User();
//            user.setEmail(String.valueOf(jsons.get("email")));
//            user.setProvider("naver");
//            user.setProviderId(String.valueOf(jsons.get("id")));
//            user.setName(String.valueOf(jsons.get("name")));
//            user.setImageUrl(String.valueOf(jsons.get("profile_image")));
//            user.setRole(Role.MEMBER);
//            userRepository.save(user);
//
//
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//    }
//    public static JsonNode getKakaoUserInfo(String accessToken) {
//
//        final String RequestUrl = "https://kapi.kakao.com/v2/user/me";
//        final HttpClient client = HttpClientBuilder.create().build();
//        final HttpPost post = new HttpPost(RequestUrl);
//
//
//        StringEntity requestEntity = new StringEntity(builder.toString() , "utf-8");
//        String data = "'property_keys=['kakao_account.email']";
//        post.setEntity(requestEntity);
//        // add header
//        post.addHeader("Authorization", accessToken);
//
//        JsonNode returnNode = null;
//
//        try {
//            final HttpResponse response = client.execute(post);
//            final int responseCode = response.getStatusLine().getStatusCode();
//
//            System.out.println("\nSending 'POST' request to URL : " + RequestUrl);
//            System.out.println("Response Code : " + responseCode);
//
//            // JSON 형태 반환값 처리
//            ObjectMapper mapper = new ObjectMapper();
//            returnNode = mapper.readTree(response.getEntity().getContent());
//
//        } catch (ClientProtocolException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            // clear resources
//        }
//
//        return returnNode;
//    }

//    public void kakaoProfile(String token) {
////        getKakaoUserInfo(token);
//        String header = "Bearer " + token; // Bearer 다음에 공백 추가
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
//            User user = new User();
//            user.setEmail("v8392070@naver.com");
//            user.setProvider("kakao");
////            LinkedHashMap responseData = (LinkedHashMap)userAttributes.get("kakao_account");
//            user.setProviderId(String.valueOf(jsonObj.get("id")));
//            user.setName(String.valueOf(jsons.get("nickname")));
//
////            user.setImageUrl((String)userAttributes.get("profile_image"));
//            user.setRole(Role.MEMBER);
//            userRepository.save(user);
//
//
////            System.out.println(response.toString());
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//    }

    public void googleProfile(String token) {
//        String header = "Bearer " + token; // Bearer 다음에 공백 추가
        try {
            String apiURL = "https://www.googleapis.com/oauth2/v3/userinfo"; // // 아직 안됨
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Authorization", token);
            con.setRequestProperty("Content-Length", "0");

            int responseCode = con.getResponseCode();

            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else { // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            System.out.println(response.toString());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

//    @PostMapping("/signup")
//    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
//        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
//            throw new BadRequestException("Email address already in use.");
//        }

    // Creating user's account
//        User user = new User();
//        user.setName(signUpRequest.getName());
//        user.setEmail(signUpRequest.getEmail());
//        user.setPassword(signUpRequest.getPassword());
//        user.setProvider(AuthProvider.local);
//
//        user.setPassword(passwordEncoder.encode(user.getPassword()));

//        User result = userRepository.save(user);

//        URI location = ServletUriComponentsBuilder
//                .fromCurrentContextPath().path("/user/me")
//                .buildAndExpand(result.getId()).toUri();

//        return ResponseEntity.created(location)
//                .body(new ApiResponse(true, "User registered successfully@"));
//        return null;
//    }


}
