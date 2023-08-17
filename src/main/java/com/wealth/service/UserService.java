package com.wealth.service;

public interface UserService {
    Integer insertAccount(String openId);
    boolean checkId(String openId);
}
