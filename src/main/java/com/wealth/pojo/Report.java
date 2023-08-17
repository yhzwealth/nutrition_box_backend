package com.wealth.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Report {
    @TableId(type = IdType.INPUT)
    private String reportId;

    private String openId;
    private Integer age;
    private Integer gender;
    private Double height;
    private Double weight;
    private Integer diet;
    private Integer vegetables;
    private Integer bean;
    private Integer milk;
    private Integer cereal;
    private Integer fish;
    private Integer drink;
    private Integer meat;
    private Integer egg;
    private Integer blood;
    private Integer sunscreen;
    private Integer sports;
    private Integer alcohol;
    private Integer smoke;
    private Integer shs;
    private Integer allergy;
    private Integer sli;
    private Integer problems;
    private Integer gums;
    private Integer ulcer;
    private Integer lack;
    private Integer operation;
    private Integer tonic;
    private Integer antibiotic;
    private Integer hear;
    private String name;

}
