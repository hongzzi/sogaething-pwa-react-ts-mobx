package com.ssafy.market.global.apis;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Slf4j
@Service
public class NewImageApi {

    @Value("${spring.global.apis.imgbb.client-id}")
    private String CLIENT_ID;


    public String uploadImg(String base64) {
        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("text/plain");
            RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                    .addFormDataPart("image", base64)
                    .build();
            Request request = new Request.Builder()
                    .url("https://api.imgbb.com/1/upload?key=" + CLIENT_ID)
                    .method("POST", body)
                    .build();
            Response response = null;

            response = client.newCall(request).execute();

            String result = response.body().string();

            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            JsonObject properties = element.getAsJsonObject().get("data").getAsJsonObject();
            String url = String.valueOf(properties.get("url"));
            url = url.replace("\"", "");

            return url;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }


}
