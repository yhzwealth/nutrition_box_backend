package com.wealth.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wealth.mapper.AddressMapper;
import com.wealth.pojo.Address;
import com.wealth.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    AddressMapper addressMapper;

    @Override
    public List<Address> getAddressByUser(String openId) {
        return addressMapper.selectList(new QueryWrapper<Address>().eq("openId",openId));
    }

    @Override
    public Integer deleteAddressById(String id) {
        return addressMapper.deleteById(id);
    }

    @Override
    public Integer insertAddress(Address add) {
        return addressMapper.insert(add);
    }

    @Override
    public Integer updateAddress(Address add) {
        return addressMapper.updateById(add);
    }

    @Override
    public boolean checkId(String id) {
        return addressMapper.selectCount(new QueryWrapper<Address>().eq("id",id))>0;
    }


}
