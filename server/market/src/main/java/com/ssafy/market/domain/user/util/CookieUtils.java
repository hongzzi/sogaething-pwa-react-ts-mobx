package com.ssafy.market.domain.user.util;

import org.springframework.http.ResponseCookie;
import org.springframework.util.SerializationUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Base64;

public class CookieUtils {

    public static Cookie getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return cookie;
                }
            }
        }

        return null;
    }

    public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
//        Cookie c2 = new Cookie(name, value);
//        c2.setPath("/");
//        c2.setHttpOnly(true);
//        c2.setMaxAge(maxAge);
//        c2.setDomain("localhost");
//        c2.isHttpOnly();
//        response.addCookie(c2);

//        Cookie cookie = new Cookie(name, value);
//        cookie.setPath("/");
//        cookie.setHttpOnly(true);
//        cookie.setMaxAge(maxAge);
////        cookie.setDomain("localhost");

//        cookie.setDomain("k02a4041.p.ssafy.io");
//        cookie.isHttpOnly();

//        response.addCookie(cookie);
        ResponseCookie cookie = ResponseCookie.from(name, value)
                .domain("k02a4041.p.ssafy.io")
                .sameSite("None")
                .secure(true)
                .path("/")
//                .httpOnly(true)
                .maxAge(maxAge)
                .build();
        response.addHeader("Set-Cookie", cookie.toString());
    }

    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie: cookies) {
                if (cookie.getName().equals(name)) {
                    cookie.setValue("");
                    cookie.setPath("/");
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
        }
    }

    public static String serialize(Object object) {
        return Base64.getUrlEncoder()
                .encodeToString(SerializationUtils.serialize(object));
    }

    public static <T> T deserialize(Cookie cookie, Class<T> cls) {
        return cls.cast(SerializationUtils.deserialize(
                        Base64.getUrlDecoder().decode(cookie.getValue())));
    }


}
