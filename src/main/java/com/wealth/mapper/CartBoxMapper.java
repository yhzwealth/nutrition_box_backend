package com.wealth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wealth.pojo.Cartbox;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartBoxMapper extends BaseMapper<Cartbox> {
    Double getPriceWithoutDiscount(String boxId);
    Integer insertProductIds(List<String> ids,String boxId);
}
