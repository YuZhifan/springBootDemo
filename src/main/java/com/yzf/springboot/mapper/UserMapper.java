package com.yzf.springboot.mapper;

import com.yzf.springboot.pojo.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    List<User> getList();

    User getOne(User user);

    List<User> getUserList(User user);

    Boolean updateUser(User user);

    Boolean deleteUser(String id);

    Integer insertUser(User user);

    Boolean batchDeleteUser(List<String> id);
}
