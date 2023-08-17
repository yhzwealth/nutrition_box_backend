package com.wealth.service;

import com.wealth.pojo.Product;

import java.util.List;

public interface CartBoxService {
    List<Product> getInnerProduct(String boxId);
    void deleteProduct(String boxId,String productId);
    List<Product> getOtherProduct(String boxId);
    Integer insertProduct(String boxId,String productId);
    boolean checkBoxId(String boxId);
    boolean checkProductId(String boxId,String productId);
    void update(List<String> productIds,String boxId,Integer month);
    Double getPriceWithoutDiscount(String boxId);
}
