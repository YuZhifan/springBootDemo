package com.yzf.springboot.admin.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.yzf.springboot.constant.Constant;
import com.yzf.springboot.pojo.dto.ResultObject;
import com.yzf.springboot.util.LoginUserHelper;
import com.yzf.springboot.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@ConfigurationProperties("interceptor")
public class SessionInterceptor extends HandlerInterceptorAdapter {

    //todo 这里应改成可配置
    private List<String> urlWhiteList = Arrays.asList("/login", "/user/add");

    private Logger logger = LoggerFactory.getLogger(SessionInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("preHandle");
            logger.debug("request servlet path:" + JSONObject.toJSONString(request.getServletPath()));
            logger.debug("request params:" + JSONObject.toJSONString(request.getParameterMap()));
        }
        if (isInWhiteList(request))
            return super.preHandle(request, response, handler);
        if (isUserLogin(request)) {
            return super.preHandle(request, response, handler);
        }
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        ResultObject result = new ResultObject();
        result.setReturnCode("F000");
        result.setReturnDesc("登录超时");
        response.getWriter().write(JSONObject.toJSONString(result));
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("postHandle");
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("afterCompletion");
        }
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("afterConcurrentHandlingStarted");
        }
    }

    private Boolean isUserLogin(HttpServletRequest request) throws Exception {
        String token = request.getHeader("token");
        if (null != token && token.contains("#")) {
            String[] tokenArray = token.split("#");
            String code = token.split("#")[0];
            if (null != code && code.length() > 0) {
                String redisToken = RedisUtil.get(Constant.REDIS_USER_PRE + code, "token");
                if (token.equals(redisToken)) {
                    String AWTCode = LoginUserHelper.getUserCodeByParseJWT(tokenArray[1]);
                    if (code.equals(AWTCode)) {
                        RedisUtil.expire(Constant.REDIS_USER_PRE + code, 1800, TimeUnit.SECONDS);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private Boolean isInWhiteList(HttpServletRequest request) {
        String currentUrl = request.getServletPath();
        return urlWhiteList.stream()
                .anyMatch(str -> currentUrl.indexOf(str) >= 0);
    }
}

