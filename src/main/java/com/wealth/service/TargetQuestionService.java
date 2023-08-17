package com.wealth.service;

import com.wealth.pojo.TargetQuestion;

import java.util.List;

public interface TargetQuestionService {
    List<TargetQuestion> getQuestionByBelongs(List<Integer> belongs);
}
