package com.yzf.springboot.controller;

import com.yzf.springboot.admin.exception.BizException;
import com.yzf.springboot.pojo.entity.User;
import com.yzf.springboot.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class SysLoginController extends BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login")
    public Object login(@RequestBody User user) throws Exception {
        if (StringUtils.isBlank(user.getCode()) || StringUtils.isBlank(user.getPwd())) {
            throw new BizException("F000", "param[code] and param[pwd] is need");
        }
        user = userService.getUser(user);
        if (null != user) {
            Boolean bool = userService.matchPwd(user.getPwd(), user.getPwd());
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


}
