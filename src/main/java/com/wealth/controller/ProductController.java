package com.wealth.controller;

import com.alibaba.fastjson.JSON;
import com.wealth.service.ProductService;
import com.wealth.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    @ResponseBody
    public Result getAllProduct(){
        return Result.success("获取成功",productService.getAllProduct());
    }

    @GetMapping("/class")
    @ResponseBody
    public Result getProductByClass(@RequestParam String cla){
        if(productService.checkClass(cla))
            return Result.success("获取成功",productService.getProductByClass(cla));
        return Result.error("该类别不存在");
    }

    @GetMapping("/info")
    @ResponseBody
    public Result getProductById(@RequestParam String id){
        if(productService.checkID(id))
            return Result.success("获取成功",productService.getProductById(id));
        return Result.error("该ID不存在");
    }

    @PostMapping("/id")
    @ResponseBody
    public Result getProductByIds(@RequestBody String str){
        List<String> ids = JSON.parseArray(JSON.parseObject(str).getString("ids"), String.class);
        return Result.success("获取成功",productService.getProductByIds(ids));
    }

    @PostMapping("/other")
    @ResponseBody
    public Result getOtherProduct(@RequestBody String str){
        List<String> ids = JSON.parseArray(JSON.parseObject(str).getString("ids"), String.class);
        return Result.success("获取成功",productService.getOtherProduct(ids));
    }
}
