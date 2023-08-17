package com.wealth.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @TableId(type = IdType.INPUT)
    private String id;

    private String openId;
    private String name;
    private String phone;
    private String address;
}
