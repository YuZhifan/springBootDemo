package com.yzf.springboot.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.yzf.springboot.mapper.UserMapper;
import com.yzf.springboot.pojo.entity.User;
import com.yzf.springboot.service.UserService;
import com.yzf.springboot.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUser(User user) {
        return userMapper.getOne(user);
    }

    @Override
    public List<User> getUserList(User user) {
//        PageHelper.startPage(1, 2);
        return userMapper.getUserList(user);
    }

    @Override
    public Boolean updateUser(User user) {
        return userMapper.updateUser(user);
    }

    @Override
    public Boolean deleteUser(String id) {
        return userMapper.deleteUser(id);
    }

    @Override
    @Transactional
    public Boolean batchDeleteUser(List<String> id) {
        return null;
    }

    @Override
    public Integer insertUser(User user) {
        return userMapper.insertUser(user);
    }

    @Override
    public Boolean matchPwd(String inputPwd, String realPwd) {
        return inputPwd.endsWith(realPwd) ? true : false;
    }

    @Override
    public String getToken(String account, String pwd) {
        return account + "#" + JWT.create().withIssuer(account)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 1800))
                .withClaim("account", account)
                .sign(Algorithm.HMAC256(pwd));
    }

    @Override
    public void saveUserTokenToRedis(String account, String token) {
        RedisUtil.put("USER_" + account, "token", token);
        RedisUtil.expire("USER_" + account, 1800, TimeUnit.SECONDS);
    }

}
