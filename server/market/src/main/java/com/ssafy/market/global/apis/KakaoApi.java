package com.ssafy.market.global.apis;

import com.ssafy.market.domain.user.domain.Role;
import com.ssafy.market.domain.user.domain.User;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Slf4j
@Service
public class KakaoApi {
    public User getUserInfo(String access_Token) {
        User user = null;
        final String reqURL = "https://kapi.kakao.com/v2/user/me";
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");

            conn.setRequestProperty("Authorization", "Bearer " + access_Token);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }

            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
            JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();

            String id = element.getAsJsonObject().get("id").getAsString();
            String nickname = properties.getAsJsonObject().get("nickname").getAsString();
//            String profile_image_url = kakao_account.getAsJsonObject().get("profile_image_url").getAsString(); // 카카오 프사 이미지 url
//            String email = kakao_account.getAsJsonObject().get("email").getAsString();

            user = new User(null, nickname, null, null, "Kakao", Long.parseLong(id), null, null, 0, Role.MEMBER);

        } catch (IOException e) {
            e.printStackTrace();
            log.error("카카오 유저정보 얻기 실패 : {}", e);
        }

        return user;
    }


}
