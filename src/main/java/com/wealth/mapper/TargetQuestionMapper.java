package com.wealth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wealth.pojo.TargetQuestion;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TargetQuestionMapper extends BaseMapper<TargetQuestion> {
    List<TargetQuestion> getQuestionByBelongs(List<Integer> belongs);
}
