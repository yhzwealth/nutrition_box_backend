package com.wealth.controller;

import com.wealth.config.WxMaProperties;
import com.wealth.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.wealth.config.WxMaConfiguration;
import com.wealth.utils.JsonUtils;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 微信小程序用户接口
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Controller
@RequestMapping("/user")
public class WxMaUserController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private WxMaProperties properties;

    @Autowired
    private UserService userService;

    /**
     * 登陆接口
     */
    @GetMapping(value = "/login",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String login(String code) {
        if (StringUtils.isBlank(code)) {
            return "empty jscode";
        }

        final WxMaService wxService = WxMaConfiguration.getMaService(properties.getConfigs().get(0).getAppid());

        try {
            WxMaJscode2SessionResult session = wxService.getUserService().getSessionInfo(code);
            //TODO 可以增加自己的逻辑，关联业务相关数据
            if(!userService.checkId(session.getOpenid()))
                userService.insertAccount(session.getOpenid());
            return JsonUtils.toJson(session);
        } catch (WxErrorException e) {
            this.logger.error(e.getMessage(), e);
            return e.toString();
        }
    }

    /**
     * <pre>
     * 获取用户信息接口
     * </pre>
     */
    @GetMapping(value = "/info",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String info(String sessionKey, String signature,
                       String rawData, String encryptedData, String iv) {
        final WxMaService wxService = WxMaConfiguration.getMaService(properties.getConfigs().get(0).getAppid());

        // 用户信息校验
        if (!wxService.getUserService().checkUserInfo(sessionKey, rawData, signature)) {
            return "user check failed";
        }

        // 解密用户信息
        WxMaUserInfo userInfo = wxService.getUserService().getUserInfo(sessionKey, encryptedData, iv);

        System.out.println(userInfo);
        return JsonUtils.toJson(userInfo);
    }

    /**
     * <pre>
     * 获取用户绑定手机号信息
     * </pre>
     */
    @GetMapping("/phone")
    @ResponseBody
    public String phone(String sessionKey, String signature,
                        String rawData, String encryptedData, String iv) {
        final WxMaService wxService = WxMaConfiguration.getMaService(properties.getConfigs().get(0).getAppid());

        // 用户信息校验
        if (!wxService.getUserService().checkUserInfo(sessionKey, rawData, signature)) {
            return "user check failed";
        }

        // 解密
        WxMaPhoneNumberInfo phoneNoInfo = wxService.getUserService().getPhoneNoInfo(sessionKey, encryptedData, iv);
        this.logger.info(phoneNoInfo.toString());
        return JsonUtils.toJson(phoneNoInfo);
    }

}
