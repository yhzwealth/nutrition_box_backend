package com.wealth.service.Impl;

import com.wealth.mapper.ReportMapper;
import com.wealth.pojo.Report;
import com.wealth.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportMapper reportMapper;

    @Override
    public void insertReport(Report report) {
        reportMapper.insert(report);
    }
}
