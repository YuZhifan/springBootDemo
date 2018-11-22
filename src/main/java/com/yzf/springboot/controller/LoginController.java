package com.yzf.springboot.controller;

import com.yzf.springboot.admin.exception.BizException;
import com.yzf.springboot.pojo.entity.User;
import com.yzf.springboot.service.UserService;
import com.yzf.springboot.util.LoginUserHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Api(description = "登录相关")
@RestController
public class LoginController extends BaseController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", value = "{\"code\":\"admin\",\"pwd\":\"admin\"}")
    })
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Object login(@RequestBody User user) throws Exception {
        if (StringUtils.isBlank(user.getCode()) || StringUtils.isBlank(user.getPwd())) {
            throw new BizException("F000", "param[code] and param[pwd] is need");
        }
        String pwd = user.getPwd();
        user = userService.getUser(user);
        if (null != user) {
            Boolean bool = userService.matchPwd(pwd, user.getPwd());
            if (bool) {
                Map<String, Object> map = new HashMap<>();
                String token = userService.getToken(user.getId(), user.getCode());
                map.put("token", token);
                userService.saveUserTokenToRedis(user.getCode(), token);
                return renderSuccess(map);
            }
        }
        return renderFail("帐号或密码错误");
    }

    @ApiOperation(value = "登出")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", value = "{}")
    })
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public Object logout(HttpServletRequest request) throws Exception {
        return renderSuccess(LoginUserHelper.cleanLoginUserSession(request));
    }


}
