package com.ssafy.market.domain.chat.util;

public class CacheKey {
    private CacheKey() {}
    public static final int DEFAULT_EXPIRE_SEC = 60;
    public static final String MESSAGE = "message";
    public static final String ROOM = "room";
    public static final String ROOMS = "rooms";
    public static final int MESSAGE_EXPIRE_SEC = 180;
    public static final int ROOM_EXPIRE_SEC = 180;
    public static final int ROOMS_EXPIRE_SEC = 180;
}
