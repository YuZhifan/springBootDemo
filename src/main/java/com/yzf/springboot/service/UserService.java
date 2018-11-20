package com.yzf.springboot.service;

import com.yzf.springboot.pojo.entity.User;

import java.util.List;

public interface UserService {

    User getUser(User user);

    List<User> getUserList(User user);

    Boolean updateUser(User user);

    Boolean deleteUser(String id);

    Boolean batchDeleteUser(List<String> id);

    Integer insertUser(User user);

    Boolean matchPwd(String inputPwd, String realPwd);

    String getToken(String id, String code) throws Exception;

    void saveUserTokenToRedis(String code, String token);
}
