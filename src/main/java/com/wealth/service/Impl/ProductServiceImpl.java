package com.wealth.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wealth.mapper.ProductMapper;
import com.wealth.pojo.Product;
import com.wealth.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> getAllProduct() {
        return productMapper.selectList(null);
    }

    @Override
    public List<Product> getProductByClass(String cla) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("classification",cla);
        return productMapper.selectByMap(map);
    }

    @Override
    public Product getProductById(String id) {
        return productMapper.selectById(id);
    }

    @Override
    public List<Product> getProductByIds(List<String> ids) {
        QueryWrapper<Product> wrapper = new QueryWrapper<>();
        for(String id:ids){
            wrapper.or().eq("id",id);
        }
        return productMapper.selectList(wrapper);
    }

    @Override
    public List<Product> getOtherProduct(List<String> ids) {
        QueryWrapper<Product> wrapper = new QueryWrapper<>();
        for(String id:ids){
            wrapper.ne("id",id);
        }
        return productMapper.selectList(wrapper);
    }

    @Override
    public boolean checkClass(String cla) {
        QueryWrapper<Product> wrapper = new QueryWrapper<>();
        wrapper.eq("classification",cla);
        return productMapper.selectCount(wrapper)>0;
    }

    @Override
    public boolean checkID(String id) {
        QueryWrapper<Product> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);
        return productMapper.selectCount(wrapper)>0;
    }
}
