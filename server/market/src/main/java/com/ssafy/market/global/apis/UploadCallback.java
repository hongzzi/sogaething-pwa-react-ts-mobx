package com.ssafy.market.global.apis;

import com.google.gson.JsonElement;

import java.util.Map;

public interface UploadCallback {

    /**
     * Called when an exception is thrown while uploading
     *
     * @param throwable thrown exception
     */
    void exception(Throwable throwable);

    /**
     * Called when the image was uploaded. Use the headers to check how many requests are remaining.
     *
     * @param headers  Map of returned headers
     * @param response json encoded response (Usually a {@link com.google.gson.JsonObject})
     */
    void uploaded(Map<String, String> headers, JsonElement response);

}