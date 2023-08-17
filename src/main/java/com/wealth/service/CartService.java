package com.wealth.service;

import com.wealth.pojo.Cart;

import java.util.List;

public interface CartService {
    List<Cart> getAllBox(String openId);
    Integer deleteBoxById(String boxId);
    Integer updateMonth(String boxId,Integer newMonth);
    boolean checkCart(String openId);
    boolean checkBoxId(String boxId);
    Integer deleteBoxByIds(List<String> boxId);
}
