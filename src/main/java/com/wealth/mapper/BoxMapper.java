package com.wealth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wealth.pojo.Box;
import org.springframework.stereotype.Repository;

@Repository
public interface BoxMapper extends BaseMapper<Box> {
    Integer moveToCart(String openId,String uuid);
    Double getPriceWithoutDiscount(String openId);
}
