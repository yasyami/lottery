package com.meyasy.user.services;

import com.alibaba.dubbo.config.annotation.Service;
import com.meyasy.user.IUserCoreService;
import org.springframework.stereotype.Component;

@Service
@Component
public class UserCoreServiceImpl implements IUserCoreService {

    @Override
    public void test() {
        System.out.println(12333);
    }
}
