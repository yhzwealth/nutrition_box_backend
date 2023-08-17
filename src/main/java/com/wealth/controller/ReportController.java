package com.wealth.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wealth.pojo.Report;
import com.wealth.pojo.Target;
import com.wealth.service.ReportService;
import com.wealth.service.TargetService;
import com.wealth.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @Autowired
    private TargetService targetService;

    @PostMapping("/insert")
    @ResponseBody
    public Result insertReport(@RequestBody String str){
        JSONObject jsonObject = JSONObject.parseObject(str);
        Report report = JSONObject.parseObject(((Map<String, Object>) jsonObject).get("report").toString(),
                                                Report.class);
        Target target = JSONObject.parseObject(((Map<String, Object>) jsonObject).get("target").toString(),
                                                Target.class);
        String uuid = UUID.randomUUID().toString();
        report.setReportId(uuid);
        target.setReportId(uuid);
        reportService.insertReport(report);
        targetService.insertTarget(target);
        return Result.success("插入成功");
    }

    @PostMapping("/getAll")
    @ResponseBody
    public Result getAllReport(@RequestBody String str){
        String openId = JSON.parseObject(str).getString("openId");
        return null;
    }
}
