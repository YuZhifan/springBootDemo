package com.yzf.springboot.controller;

import com.yzf.springboot.pojo.entity.User;
import com.yzf.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class SysLoginController extends BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login")
    public Object login(String account, String pwd) {
        User user = new User();
        user.setCode(account);
        user.setType(0);
        user = userService.getUser(user);
        if (null != user) {
            Boolean bool = userService.matchPwd(pwd, user.getPwd());
            if (bool) {
                Map<String, Object> map = new HashMap<>();
                String token = userService.getToken(account, pwd);
                map.put("token", token);
                userService.saveUserTokenToRedis(account, token);
                return renderSuccess(map);
            }
        }
        return renderFail("帐号或密码错误");
    }
}
