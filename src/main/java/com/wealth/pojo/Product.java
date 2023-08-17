package com.wealth.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @TableId(type = IdType.INPUT)
    private String id;

    private String productName;
    private Integer dosage;
    private Double price;
    private String picPath;
    private String description;
    private String classification;
}
