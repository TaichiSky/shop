package com.example.shop.service;

import com.example.shop.dao.UserMapper;
import com.example.shop.dto.User;
import com.example.shop.dto.UserExample;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    //为了避免循环依赖，这里不使用 @Autowired 注解，虽然这里仅仅是单表的增删改查，要养成好习惯
    private final UserMapper userMapper;

    @Override
    public Optional<List<User>> getAll() {
        return Optional.ofNullable(userMapper.selectByExample(new UserExample()));
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return Optional.ofNullable(userMapper.selectByPrimaryKey(id));
    }

    @Override
    public Optional<List<User>> pageList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return Optional.ofNullable(userMapper.selectByExample(new UserExample()));
    }

    @Override
    public int add(User user) {
        return userMapper.insertSelective(user);
    }

    @Override
    public int update(Long id, User user) {
        user.setId(id);
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public int delete(Long id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int deleteBatch(List<Long> ids) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andIdIn(ids);
        return userMapper.deleteByExample(userExample);
    }

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
}
