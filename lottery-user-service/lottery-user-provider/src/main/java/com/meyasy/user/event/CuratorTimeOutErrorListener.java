package com.meyasy.user.event;


import org.springframework.boot.context.event.ApplicationContextInitializedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class CuratorTimeOutErrorListener implements ApplicationListener<ContextRefreshedEvent>{

//     int DEFAULT_SESSION_TIMEOUT_MS = Integer.getInteger("curator-default-session-timeout", 60 * 1000);
//     int DEFAULT_CONNECTION_TIMEOUT_MS = Integer.getInteger("curator-default-connection-timeout", 15 * 1000);


     @Override
     public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
          System.setProperty("curator-default-session-timeout","600000");
          System.setProperty("curator-default-connection-timeout","300000");
     }
}
