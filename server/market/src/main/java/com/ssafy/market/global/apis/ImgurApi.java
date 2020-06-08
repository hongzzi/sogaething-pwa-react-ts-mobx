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
            if (response.statusCode() == 400) {
                return "false";
            }
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(response.body());
            JsonObject properties = element.getAsJsonObject().get("data").getAsJsonObject();
            String url = String.valueOf(properties.get("link"));
            url = url.replace("\"", "");
            return url;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



    public Connection.Response uploadSync(String base64) throws IOException {
        Connection connection = Jsoup.connect("https://api.imgur.com/3/image");
        connection.timeout(10000).userAgent("InventiveImgurUploader").ignoreContentType(true).ignoreHttpErrors(true).method(Connection.Method.POST);
        connection.header("Authorization", "Client-ID " + CLIENT_ID);
        connection.header("Content-Type", "application/x-www-form-urlencoded");
        connection.data("image", base64);
        return connection.execute();
    }
}
