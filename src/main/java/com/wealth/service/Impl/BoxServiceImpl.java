package com.wealth.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wealth.mapper.BoxMapper;
import com.wealth.mapper.CartBoxMapper;
import com.wealth.mapper.CartMapper;
import com.wealth.mapper.ProductMapper;
import com.wealth.pojo.Box;
import com.wealth.pojo.Cart;
import com.wealth.pojo.Product;
import com.wealth.service.BoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BoxServiceImpl implements BoxService {

    @Autowired
    private BoxMapper boxMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private CartBoxMapper cartBoxMapper;

    @Override
    public Integer putIntoBox(String openId, String productId) {
        return boxMapper.insert(new Box(openId,productId));
    }

    @Override
    public Integer putOutBox(String openId, String productId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("openId",openId);
        map.put("productId",productId);
        return boxMapper.deleteByMap(map);
    }

    public List<String> productList(String openId){
        QueryWrapper<Box> wrapper = new QueryWrapper<>();
        wrapper.eq("openId",openId);
        List<Box> list = boxMapper.selectList(wrapper);
        return list.stream().map(Box::getProductId).collect(Collectors.toList());
    }

    @Override
    public List<Product> getBoxProduct(String openId) {
        List<String> list = productList(openId);
        if(list.isEmpty())
            return null;
        QueryWrapper<Product> wrapper = new QueryWrapper<>();
        wrapper.in("id",list);
        return productMapper.selectList(wrapper);
    }

    @Override
    public List<Product> getOtherProduct(String openId) {
        List<String> list = productList(openId);
        QueryWrapper<Product> wrapper = new QueryWrapper<>();
        wrapper.notIn("id",list);
        return productMapper.selectList(wrapper);
    }

    @Override
    public boolean checkProduct(String openId, String productId) {
       QueryWrapper<Box> wrapper = new QueryWrapper<>();
       wrapper.eq("openId",openId)
           .eq("productId",productId);
       return boxMapper.selectCount(wrapper)>0;
    }

    @Override
    public Integer moveToCart(String openId,Integer month) {
        UUID uuid = UUID.randomUUID();
        boxMapper.moveToCart(openId,uuid.toString());
        Double discount=month==3?0.9:1.0;
        return cartMapper.insert(new Cart(openId,uuid.toString(),month,
            cartBoxMapper.getPriceWithoutDiscount(uuid.toString())*discount));
    }

    @Override
    public boolean checkBox(String openId) {
        QueryWrapper<Box> wrapper = new QueryWrapper<>();
        wrapper.eq("openId",openId);
        return boxMapper.selectCount(wrapper)>0;
    }

    @Override
    public Double getPriceWithoutDiscount(String openId) {
        return boxMapper.getPriceWithoutDiscount(openId);
    }
}
