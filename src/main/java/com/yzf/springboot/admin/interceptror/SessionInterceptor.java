package com.yzf.springboot.admin.interceptror;

import com.alibaba.fastjson.JSONObject;
import com.yzf.springboot.constant.Constant;
import com.yzf.springboot.pojo.dto.ResultObject;
import com.yzf.springboot.util.AESUtil;
import com.yzf.springboot.util.JWTUtil;
import com.yzf.springboot.util.LoginUserHelper;
import com.yzf.springboot.util.RedisUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class SessionInterceptor extends HandlerInterceptorAdapter {

    private Logger logger = LoggerFactory.getLogger(SessionInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String currentUrl = request.getServletPath();
        Map<String, String[]> params = request.getParameterMap();
        logger.debug("preHandle");
        logger.debug("request servlet path:" + JSONObject.toJSONString(currentUrl));
        logger.debug("request params:" + JSONObject.toJSONString(params));
        if (currentUrl.indexOf("/login") >= 0 || currentUrl.indexOf("/user/add") >= 0)
            return super.preHandle(request, response, handler);
        if (isUserLogin(request.getHeader("token"))) {
            return super.preHandle(request, response, handler);
        }
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        ResultObject result = new ResultObject();
        result.setReturnCode("F000");
        result.setReturnMsg("登录超时");
        response.getWriter().write(JSONObject.toJSONString(result));
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        logger.debug("postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        logger.debug("afterCompletion");
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.debug("afterConcurrentHandlingStarted");
    }

    private Boolean isUserLogin(String token) throws Exception {
        if (null != token && token.contains("#")) {
            String[] tokenArray = token.split("#");
            String code = token.split("#")[0];
            if (null != code && code.length() > 0) {
                String redisToken = RedisUtil.get("USER_" + code, "token");
                if (token.equals(redisToken)) {
                    String AWTCode = LoginUserHelper.getUserCodeByParseJWT(tokenArray[1]);
                    if (code.equals(AWTCode)) {
                        RedisUtil.expire("USER_" + code, 1800, TimeUnit.SECONDS);
                        return true;
                    }
                }
            }
        }
        return false;
    }

}

