package com.wealth.controller;

import com.alibaba.fastjson.JSON;
import com.wealth.service.BoxService;
import com.wealth.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/box")
public class BoxController {

    @Autowired
    private BoxService boxService;

    @PostMapping("/into")
    @ResponseBody
    public Result putIntoBox(@RequestBody String str){
        String openId = JSON.parseObject(str).getString("openId");
        String productId = JSON.parseObject(str).getString("productId");
        if(!boxService.checkProduct(openId, productId))
            return Result.success("已加入补充盒",boxService.putIntoBox(openId,productId));
        return Result.error("该商品已存在补充盒中");
    }

    @PostMapping("/out")
    @ResponseBody
    public Result putOutBox(@RequestBody String str){
        String openId = JSON.parseObject(str).getString("openId");
        String productId = JSON.parseObject(str).getString("productId");
        if(boxService.checkProduct(openId, productId))
            return Result.success("已从补充盒中移出",boxService.putOutBox(openId,productId));
        return Result.error("该商品不在补充盒中");
    }

    @PostMapping("/get")
    @ResponseBody
    public Result getBoxProduct(@RequestBody String openId){
        openId = JSON.parseObject(openId).getString("openId");
        return Result.success("获取成功",boxService.getBoxProduct(openId));
    }

    @PostMapping("/other")
    @ResponseBody
    public Result getOtherProduct(@RequestBody String openId){
        openId = JSON.parseObject(openId).getString("openId");
        return Result.success("获取成功",boxService.getOtherProduct(openId));
    }

    @PostMapping("/move")
    @ResponseBody
    public Result moveToCart(@RequestBody String str){
        System.out.println("move");
        String openId = JSON.parseObject(str).getString("openId");
        Integer month = JSON.parseObject(str).getInteger("month");
        if(boxService.checkBox(openId))
            return Result.success("已添加至购物车",boxService.moveToCart(openId, month));
        return Result.error("该账号补充包为空，无法移至购物车");
    }

    @PostMapping("/price")
    @ResponseBody
    public Result getPrice(@RequestBody String openId){
        openId = JSON.parseObject(openId).getString("openId");
        return Result.success(boxService.getPriceWithoutDiscount(openId));
    }

}
