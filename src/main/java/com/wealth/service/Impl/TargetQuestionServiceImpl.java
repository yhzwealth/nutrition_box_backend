package com.wealth.service.Impl;

import com.wealth.mapper.TargetQuestionMapper;
import com.wealth.pojo.TargetQuestion;
import com.wealth.service.TargetQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TargetQuestionServiceImpl implements TargetQuestionService {

    @Autowired
    private TargetQuestionMapper targetQuestionMapper;

    @Override
    public List<TargetQuestion> getQuestionByBelongs(List<Integer> belongs) {
        return targetQuestionMapper.getQuestionByBelongs(belongs);
    }
}
