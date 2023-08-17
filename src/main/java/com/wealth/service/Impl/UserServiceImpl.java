package com.wealth.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wealth.mapper.UserMapper;
import com.wealth.pojo.User;
import com.wealth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Integer insertAccount(String openId) {
        User user = new User();
        user.setOpenId(openId);
        return userMapper.insert(user);
    }

    @Override
    public boolean checkId(String openId) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("openId",openId);
        return userMapper.selectCount(wrapper)>0;
    }
}
