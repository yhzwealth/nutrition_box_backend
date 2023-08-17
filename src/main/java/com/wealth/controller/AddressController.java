package com.wealth.controller;

import com.wealth.pojo.Address;
import com.wealth.service.AddressService;
import com.wealth.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/address")
public class AddressController {

    @Autowired
    AddressService addressService;

    @ResponseBody
    @PostMapping("/get")
    public Result getAddressByUser(@RequestBody String openId){
        return Result.success("查询成功",addressService.getAddressByUser(openId));
    }

    @ResponseBody
    @PostMapping("/delete")
    public Result deleteAddressById(@RequestBody String id){
        if(addressService.checkId(id)) {
            addressService.deleteAddressById(id);
            return Result.success("删除成功");
        }
        return Result.error("该ID不存在");
    }

    @ResponseBody
    @PostMapping("/insert")
    public Result insertAddress(@RequestBody Address add){
        if(add.getName()!=null&&add.getPhone()!=null&&add.getAddress()!=null) {
            add.setId(UUID.randomUUID().toString());
            addressService.insertAddress(add);
            return Result.success("插入成功");
        }
        return Result.error("数据不全");
    }

    @ResponseBody
    @PostMapping("/update")
    public Result updateAddress(@RequestBody Address add){
        if(addressService.checkId(add.getId())) {
            addressService.updateAddress(add);
            return Result.success("修改成功");
        }
        return Result.error("输入的地址ID有误");
    }
}
