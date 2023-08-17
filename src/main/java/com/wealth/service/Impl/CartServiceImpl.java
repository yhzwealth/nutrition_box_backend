package com.wealth.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wealth.mapper.CartBoxMapper;
import com.wealth.mapper.CartMapper;
import com.wealth.pojo.Cart;
import com.wealth.pojo.Cartbox;
import com.wealth.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private CartBoxMapper cartBoxMapper;

    @Override
    public List<Cart> getAllBox(String openId) {
        QueryWrapper<Cart> wrapper = new QueryWrapper<>();
        wrapper.eq("openId",openId);
        return cartMapper.selectList(wrapper);
    }

    @Override
    public Integer deleteBoxById(String boxId) {
        QueryWrapper<Cartbox> wrapper = new QueryWrapper<>();
        wrapper.eq("boxId",boxId);
        cartBoxMapper.delete(wrapper);
        return cartMapper.deleteById(boxId);
    }

    @Override
    public Integer updateMonth(String boxId,Integer newMonth) {
        Cart cart = new Cart();
        cart.setMonth(newMonth);
        cart.setBoxId(boxId);
        return cartMapper.updateById(cart);
    }

    @Override
    public boolean checkCart(String openId) {
        QueryWrapper<Cart> wrapper = new QueryWrapper<>();
        wrapper.eq("openId",openId);
        return cartMapper.selectCount(wrapper)>0;
    }

    @Override
    public boolean checkBoxId(String boxId) {
        QueryWrapper<Cart> wrapper = new QueryWrapper<>();
        wrapper.eq("boxId",boxId);
        return cartMapper.selectCount(wrapper)>0;
    }

    @Override
    public Integer deleteBoxByIds(List<String> boxId) {
        QueryWrapper<Cart> wrapper1 = new QueryWrapper<>();
        QueryWrapper<Cartbox> wrapper2 = new QueryWrapper<>();
        for(String id:boxId){
            wrapper1.or().eq("boxId",id);
            wrapper2.or().eq("boxId",id);
        }
        cartBoxMapper.delete(wrapper2);
        return cartMapper.delete(wrapper1);
    }
}
