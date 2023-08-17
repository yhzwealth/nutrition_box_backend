package com.wealth.service;

import com.wealth.pojo.Address;

import java.util.List;

public interface AddressService {
    List<Address> getAddressByUser(String openId);
    Integer deleteAddressById(String id);
    Integer insertAddress(Address add);
    Integer updateAddress(Address add);
    boolean checkId(String id);
}
