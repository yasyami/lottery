package com.meyasy.user;

import com.meyasy.user.dao.entity.User;
import com.meyasy.user.dao.persistence.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * Hello world!
 */
@SpringBootApplication
public class UserProviderApp {


    private static UserMapper userMapper;
    @Autowired
    public UserProviderApp( UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(UserProviderApp.class).web(WebApplicationType.NONE).run(args);

        User userByUid = userMapper.getUserByUid(1);

        System.out.println(userByUid.getRealname());
    }



}
