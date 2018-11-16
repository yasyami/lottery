package com.meyasy.user.services;

import com.alibaba.dubbo.config.annotation.Service;
import com.meyasy.user.IUserCoreService;
import com.meyasy.user.dto.*;
import org.springframework.beans.factory.annotation.Value;

@Service(version = "${demo.service.version}",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}")
public class UserCoreServiceImpl implements IUserCoreService {

    @Override
    public UserLoginResponse login(UserLoginRequest request) {
        return null;
    }

    @Override
    public CheckAuthResponse validToken(CheckAuthRequest request) {
        return null;
    }

    @Override
    public UserRegisterResponse register(UserLoginRequest request) {
        return null;
    }
}
