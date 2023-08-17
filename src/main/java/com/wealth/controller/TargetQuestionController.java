package com.wealth.controller;

import com.alibaba.fastjson.JSON;
import com.wealth.service.TargetQuestionService;
import com.wealth.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/TQuestion")
public class TargetQuestionController {
    @Autowired
    private TargetQuestionService targetQuestionService;

    @ResponseBody
    @GetMapping("getTQuestion")
    public Result getQuestionByBelongs(@RequestBody String str){
        List<Integer> belongs = JSON.parseArray(JSON.parseObject(str).getString("belongs"), Integer.class);
        ArrayList<Integer> integers = new ArrayList<>();
        for(Integer i : belongs)
            integers.add((int) (Math.log(i)/Math.log(2)));
        return Result.success("获取成功",targetQuestionService.getQuestionByBelongs(integers));
    }

}
