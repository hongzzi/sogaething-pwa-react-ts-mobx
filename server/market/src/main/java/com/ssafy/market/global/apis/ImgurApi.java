//package com.ssafy.market.global.apis;
//
//import com.google.common.net.MediaType;
//import org.apache.http.NameValuePair;
//import org.apache.http.client.ClientProtocolException;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.message.BasicNameValuePair;
//import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.RequestBody;
//
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class ImgurApi {
//    public static final String CLIENT_ID = "2d1537da8393cd6";
//    public static final String IMGUR_URL = "https://api.imgur.com/3/image";
//
//    public String getUrlLink(String base64String, String imageLink){
//
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        HttpPost httpPostRequest = new HttpPost(IMGUR_URL);
//        httpPostRequest.setHeader("Authorization", "Client-ID " + CLIENT_ID);
//
//        List<NameValuePair> params = new ArrayList<NameValuePair>();
//        params.add(new BasicNameValuePair("image", base64String));
//
//        return null;
//    }
//
//    public String getLink(String base64String){
//
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        HttpPost httpPostRequest = new HttpPost(IMGUR_URL);
//        httpPostRequest.setHeader("Authorization", "Client-ID " + CLIENT_ID);
//
//        List<NameValuePair> params = new ArrayList<NameValuePair>();
//        params.add(new BasicNameValuePair("image", base64String));
//
//        return null;
//    }
//
//    public void uploadImage(String base64String, String imageLink){
//
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        HttpPost httpPostRequest = new HttpPost(IMGUR_URL);
//        httpPostRequest.setHeader("Authorization", "Client-ID " + CLIENT_ID);
//
//        List<NameValuePair> params = new ArrayList<NameValuePair>();
//        params.add(new BasicNameValuePair("image", base64String));
//        CustomResponseHandler customResponseHandler = new CustomResponseHandler();
//        int status = -1;
//        try {
//            httpPostRequest.setEntity(new UrlEncodedFormEntity(params));
//            ResponseObject responseBody = (ResponseObject) httpClient.execute(httpPostRequest, customResponseHandler);
//
//            status = responseBody.getStatusCode();
//            if(status>=200 && status<300){
//                jobUrlLists.getPending().remove(imageLink);
//                jobUrlLists.getCompleted().add(responseBody.getLink());
//                if(jobUrlLists.getPending().isEmpty()) {
//                    jobUrlLists.setStatus("processed");
//                }
//            } else {
//                jobUrlLists.getPending().remove(imageLink);
//                jobUrlLists.getFailed().add(imageLink);
//            }
//
//            httpClient.close();
//        }
//
//    }
//}
