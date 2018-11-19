package com.meyasy.sso.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.meyasy.user.IUserQueryService;
import com.meyasy.user.dto.UserQueryRequest;
import com.meyasy.user.dto.UserQueryResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @Reference
    IUserQueryService userQueryService;


    @GetMapping("/")
    private UserQueryResponse index(){
        UserQueryRequest request = new UserQueryRequest();
        request.setUid(1);
        UserQueryResponse user = userQueryService.getUserById(request);
        return user;
    }


}
