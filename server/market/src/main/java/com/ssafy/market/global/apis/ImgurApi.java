package com.ssafy.market.global.apis;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ImgurApi {

    @Value("${spring.global.apis.imgur.client-id}")
    private String CLIENT_ID;

    public String uploadImg(String base64){
        try {
            Connection.Response response = uploadSync(base64);
            System.out.println(response.statusCode());
            System.out.println(response.statusMessage());
            System.out.println(response.body());

            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(response.body());
            JsonObject properties = element.getAsJsonObject().get("data").getAsJsonObject();
            String url = String.valueOf(properties.get("link"));
            url = url.replace("\"", "");
            if (response.statusCode() == 400) {
                return "false";
            }
            return url;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



    public Connection.Response uploadSync(String base64) throws IOException {
        Connection connection = Jsoup.connect("https://api.imgur.com/3/image");
        connection.timeout(10000).ignoreContentType(true).ignoreHttpErrors(true).method(Connection.Method.POST);
        connection.header("Authorization", "Client-ID " + CLIENT_ID);
//        connection.header("key", " 677e3542d670598837602dfb2f20814f");
//        connection.header("Content-Type", "application/x-www-form-urlencoded");
        connection.data("image", base64);
        return connection.execute();
    }
}
