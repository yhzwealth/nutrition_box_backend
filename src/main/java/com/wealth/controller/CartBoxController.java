package com.wealth.controller;

import com.alibaba.fastjson.JSON;
import com.wealth.service.CartBoxService;
import com.wealth.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cartBox")
public class CartBoxController {

    @Autowired
    private CartBoxService cartBoxService;

    @ResponseBody
    @GetMapping("/getAll")
    public Result getAllProducts(@RequestParam String boxId){
        if(cartBoxService.checkBoxId(boxId))
            return Result.success("获取成功",cartBoxService.getInnerProduct(boxId));
        return Result.error("该boxId不存在");
    }

    @ResponseBody
    @GetMapping("/getOther")
    public Result getOtherProducts(@RequestParam String boxId){
        if(cartBoxService.checkBoxId(boxId))
            return Result.success("获取成功",cartBoxService.getOtherProduct(boxId));
        return Result.error("该boxId不存在");
    }

    @ResponseBody
    @PostMapping("/delete")
    public Result deleteProduct(@RequestBody String str){
        String boxId = JSON.parseObject(str).getString("boxId");
        String productId = JSON.parseObject(str).getString("productId");
        if (cartBoxService.checkProductId(boxId, productId)) {
            cartBoxService.deleteProduct(boxId, productId);
            return Result.success("删除成功");
        }
        return Result.error("输入的productId有误");
    }

    @ResponseBody
    @PostMapping("/insert")
    public Result insertProduct(@RequestBody String str){
        System.out.println("insert");
        String boxId = JSON.parseObject(str).getString("boxId");
        String productId = JSON.parseObject(str).getString("productId");
        if (!cartBoxService.checkProductId(boxId, productId)) {
            cartBoxService.insertProduct(boxId, productId);
            return Result.success("添加成功");
        }
        return Result.error("输入的productId有误");
    }

    @ResponseBody
    @PostMapping("/update")
    public Result updateCartBox(@RequestBody String str){
        System.out.println("update");
        List<String> productIds = JSON.parseArray(JSON.parseObject(str).getString("productIds"), String.class);
        String boxId = JSON.parseObject(str).getString("boxId");
        Integer month = JSON.parseObject(str).getInteger("month");
        if(cartBoxService.checkBoxId(boxId)){
            cartBoxService.update(productIds, boxId, month);
            return Result.success("更新成功");
        }
        return Result.error("boxId有误");
    }
}
