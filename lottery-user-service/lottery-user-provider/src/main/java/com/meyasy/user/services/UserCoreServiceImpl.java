package com.meyasy.user.services;

import com.alibaba.dubbo.config.annotation.Service;
import com.meyasy.user.IUserCoreService;
import org.springframework.beans.factory.annotation.Value;

@Service(version = "${demo.service.version}",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}")
public class UserCoreServiceImpl implements IUserCoreService {

    @Value("${dubbo.registry.address}")
    private  String registerId ;

    @Override
    public void test() {
        System.out.println(registerId);
    }
}
