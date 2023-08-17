package com.wealth.service;

import com.wealth.pojo.Product;

import java.util.List;

public interface BoxService {
    Integer putIntoBox(String openId,String productId);
    Integer putOutBox(String openId,String productId);
    List<Product> getBoxProduct(String openId);
    List<Product> getOtherProduct(String openId);
    boolean checkProduct(String openId,String productId);
    Integer moveToCart(String openId,Integer month);
    boolean checkBox(String openId);
    Double getPriceWithoutDiscount(String openId);
}
