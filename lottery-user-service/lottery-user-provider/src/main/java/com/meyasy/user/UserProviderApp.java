package com.meyasy.user;

import com.meyasy.user.entity.User;
import com.meyasy.user.persistence.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * Hello world!
 */
@SpringBootApplication
public class UserProviderApp {


    private static UserDao userDao;
    @Autowired
    public UserProviderApp( UserDao userDao) {
        this.userDao = userDao;
    }

    public static void main(String[] args) {
        System.setProperty("curator-default-session-timeout","60000");
        System.setProperty("curator-default-connection-timeout","30000");
        new SpringApplicationBuilder(UserProviderApp.class).web(WebApplicationType.NONE).run(args);

        User userByUid = userDao.getUserByUid(1);

        System.out.println(userByUid.getRealname());
    }



}
