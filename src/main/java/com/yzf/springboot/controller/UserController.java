package com.yzf.springboot.controller;

import com.yzf.springboot.admin.exception.BizException;
import com.yzf.springboot.pojo.entity.User;
import com.yzf.springboot.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/user")
@RestController
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/list")
    public Object list(User user) {
        return renderSuccess(userService.getUserList(user));
    }

    @RequestMapping(value = "/add")
    public Object add(@RequestBody User user) throws BizException {
        if (StringUtils.isBlank(user.getCode())) {
            throw new BizException("F000", "param[code] is need");
        }
        if (StringUtils.isBlank(user.getName())) {
            throw new BizException("F000", "param[name] is need");
        }
        if (StringUtils.isBlank(user.getPwd())) {
            throw new BizException("F000", "param[pwd] is need");
        }
        if (null != userService.getUser(user)) {
            throw new BizException("F000", "code[" + user.getCode() + "] is exist");
        }
        return renderSuccess(userService.insertUser(user));
    }

    @RequestMapping(value = "/delete")
    public Object delete(String id) throws BizException {
        if (StringUtils.isBlank(id)) {
            throw new BizException("F000", "param[id] is need");
        }
        return renderSuccess(userService.deleteUser(id));
    }

    @RequestMapping(value = "/update")
    public Object update(User user) throws BizException {
        if (StringUtils.isBlank(user.getId())) {
            throw new BizException("F000", "param[id] is need");
        }
        return renderSuccess(userService.updateUser(user));
    }
}
