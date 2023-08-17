package com.wealth.service.Impl;

import com.wealth.mapper.TargetMapper;
import com.wealth.pojo.Target;
import com.wealth.service.TargetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TargetServiceImpl implements TargetService {

    @Autowired
    private TargetMapper targetMapper;

    @Override
    public void insertTarget(Target target) {
        targetMapper.insert(target);
    }
}
