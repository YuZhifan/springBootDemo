package com.yzf.springboot.util;

import com.yzf.springboot.constant.Constant;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class LoginUserHelper {

    public static String getUserCodeByParseJWT(String jwt) throws Exception {
        Claims claims = JWTUtil.parseJWT(jwt);
        if (null == claims) {
            return null;
        }
        Object object = claims.get("userCode");
        if (null == object) {
            return null;
        }
        return String.valueOf(object);
    }

    public static String getUserIdByParseJWT(String jwt) throws Exception {
        Claims claims = JWTUtil.parseJWT(jwt);
        if (null == claims) {
            return null;
        }
        Object object = claims.get("userId");
        if (null == object) {
            return null;
        }
        return String.valueOf(object);
    }

    /**
     *
     */
    public static String getLoginUserCode(HttpServletRequest request) throws Exception {
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            return null;
        }
        String[] tokenArray = token.split("#");
        return getUserCodeByParseJWT(tokenArray[1]);
    }

    /**
     *
     */
    public static String getLoginUserId(HttpServletRequest request) throws Exception {
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            return null;
        }
        String[] tokenArray = token.split("#");
        return getUserIdByParseJWT(tokenArray[1]);
    }

    /**
     *
     */
    public static Boolean cleanLoginUserSession(HttpServletRequest request) {
        String token = request.getHeader("token");
        String[] tokenArray = token.split("#");
        RedisUtil.delete(Constant.REDIS_USER_PRE  + tokenArray[0]);
        return true;
    }
}
