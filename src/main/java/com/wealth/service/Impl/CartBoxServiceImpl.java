package com.wealth.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wealth.mapper.CartBoxMapper;
import com.wealth.mapper.CartMapper;
import com.wealth.mapper.ProductMapper;
import com.wealth.pojo.Cart;
import com.wealth.pojo.Cartbox;
import com.wealth.pojo.Product;
import com.wealth.service.CartBoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartBoxServiceImpl implements CartBoxService {

    @Autowired
    private CartBoxMapper cartBoxMapper;

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private ProductMapper productMapper;

    private List<String> getBoxIdList(String boxId){
        QueryWrapper<Cartbox> wrapper = new QueryWrapper<>();
        wrapper.eq("boxId",boxId);
        List<Cartbox> list = cartBoxMapper.selectList(wrapper);
        return list.stream().map(Cartbox::getProductId).collect(Collectors.toList());
    }

    @Override
    public List<Product> getInnerProduct(String boxId) {
        QueryWrapper<Product> wrapper = new QueryWrapper<>();
        wrapper.in("id",getBoxIdList(boxId));
        return productMapper.selectList(wrapper);
    }

    @Override
    public void deleteProduct(String boxId, String productId) {
        QueryWrapper<Cartbox> wrapper = new QueryWrapper<>();
        wrapper.eq("boxId",boxId)
            .eq("productId",productId);
        cartBoxMapper.delete(wrapper);
        QueryWrapper<Cartbox> w = new QueryWrapper<>();
        w.eq("boxId",boxId);
        if(cartBoxMapper.selectCount(w)==0)
            cartMapper.deleteById(boxId);

    }

    @Override
    public List<Product> getOtherProduct(String boxId) {
        QueryWrapper<Product> wrapper = new QueryWrapper<>();
        wrapper.notIn("id",getBoxIdList(boxId));
        return productMapper.selectList(wrapper);
    }

    @Override
    public Integer insertProduct(String boxId, String productId) {
        Cartbox cartBox = new Cartbox(boxId,productId);
        return cartBoxMapper.insert(cartBox);
    }

    @Override
    public boolean checkBoxId(String boxId) {
        QueryWrapper<Cartbox> wrapper= new QueryWrapper<>();
        wrapper.eq("boxId",boxId);
        return cartBoxMapper.selectCount(wrapper)>0;
    }

    @Override
    public boolean checkProductId(String boxId,String productId) {
        QueryWrapper<Cartbox> wrapper= new QueryWrapper<>();
        wrapper.eq("boxId",boxId)
            .eq("productId",productId);
        return cartBoxMapper.selectCount(wrapper)>0;
    }

    @Override
    public void update(List<String> productIds, String boxId,Integer month) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("boxId",boxId);
        cartBoxMapper.deleteByMap(map);
        cartBoxMapper.insertProductIds(productIds,boxId);
        Cart cart = new Cart();
        cart.setBoxId(boxId);
        cart.setMonth(month);
        Double discount=month==3?0.9:1.0;
        cart.setTotal(cartBoxMapper.getPriceWithoutDiscount(boxId)*discount);
        cartMapper.updateById(cart);
    }

    @Override
    public Double getPriceWithoutDiscount(String boxId) {
        return cartBoxMapper.getPriceWithoutDiscount(boxId);
    }
}
