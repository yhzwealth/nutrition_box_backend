package com.wealth.controller;

import com.alibaba.fastjson.JSON;
import com.wealth.service.CartService;
import com.wealth.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @ResponseBody
    @PostMapping("/get")
    public Result getAllBox(@RequestBody String openId){
        openId = JSON.parseObject(openId).getString("openId");
        if(cartService.checkCart(openId))
            return Result.success("获取成功",cartService.getAllBox(openId));
        return Result.error("该账号购物车为空");
    }

    @ResponseBody
    @PostMapping("/delete")
    public Result deleteBox(@RequestBody String boxId){
        boxId = JSON.parseObject(boxId).getString("boxId");
        if(cartService.checkBoxId(boxId))
            return Result.success("删除成功",cartService.deleteBoxById(boxId));
        return Result.error("该补给包不存在");
    }

    @ResponseBody
    @PostMapping("/deleteIds")
    public Result deleteBoxByIds(@RequestBody String str){
        List<String> ids = JSON.parseArray(JSON.parseObject(str).getString("boxIds"), String.class);
        return Result.success("删除成功",cartService.deleteBoxByIds(ids));
    }

    @ResponseBody
    @PostMapping("/month")
    public Result updateMonth(@RequestBody String str){
        String boxId = JSON.parseObject(str).getString("boxId");
        Integer newMonth = JSON.parseObject(str).getInteger("newMonth");
        if(cartService.checkBoxId(boxId))
            return Result.success("修改成功",cartService.updateMonth(boxId, newMonth));
        return Result.error("该补给包不存在");
    }
}
