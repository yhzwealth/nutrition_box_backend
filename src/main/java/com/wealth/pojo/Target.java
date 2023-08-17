package com.wealth.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Target {
    @TableId(type = IdType.INPUT)
    private String reportId;

    private Integer classification;
    private Integer mainTarget;
    private Integer question1;
    private Integer question2;
    private Integer question3;
    private Integer question4;
    private Integer question5;
    private Integer question6;
    private Integer question7;
    private Integer question8;
    private Integer question9;
    private Integer question10;
    private Integer question11;
    private Integer question12;
    private Integer question13;
    private Integer question14;
    private Integer question15;
}

