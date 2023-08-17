package com.wealth.service;

import com.wealth.pojo.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProduct();
    List<Product> getProductByClass(String cla);
    Product getProductById(String id);
    List<Product> getProductByIds(List<String> ids);
    List<Product> getOtherProduct(List<String> ids);
    boolean checkClass(String cla);
    boolean checkID(String id);
}
