package com.yzf.springboot.service.impl;

import com.yzf.springboot.mapper.UserMapper;
import com.yzf.springboot.pojo.entity.User;
import com.yzf.springboot.service.UserService;
import com.yzf.springboot.util.AESUtil;
import com.yzf.springboot.util.JWTUtil;
import com.yzf.springboot.util.RedisUtil;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public String getToken(String id, String code) throws Exception {
        return code + "#" + JWTUtil.createJWT(id, code, 1800000);
    }


    @Override
    public void saveUserTokenToRedis(String code, String token) {
        RedisUtil.put("USER_" + code, "token", token);
        RedisUtil.expire("USER_" + code, 1800, TimeUnit.SECONDS);
    }



}
