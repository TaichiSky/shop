package com.example.shop.service;

import com.example.shop.dto.User;
import com.sun.istack.internal.NotNull;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<List<User>> getAll();  //查询所有用户

    Optional<User> getUserById(@NotNull Long id);   //根据 id 查询用户

    Optional<List<User>> pageList(int pageNum, int pageSize);   //分页查询用户

    int add(User user); //添加单个用户

    int update(@NotNull Long id, User user);    //根据 id 更新用户信息

    int delete(@NotNull Long id);    //删除单条数据

    int deleteBatch(List<Long> ids);    //插件自带方法批量删除
}
