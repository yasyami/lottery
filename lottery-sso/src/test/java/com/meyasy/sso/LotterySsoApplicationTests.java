package com.meyasy.sso;

import com.alibaba.dubbo.config.annotation.Reference;
import com.meyasy.user.IUserCoreService;
import com.meyasy.user.dto.UserLoginRequest;
import com.meyasy.user.dto.UserLoginResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LotterySsoApplicationTests {

	@Reference
	IUserCoreService userCoreService;

	@Test
	public void contextLoads() {
		UserLoginRequest request = null;
		UserLoginResponse login = userCoreService.login(request);
		System.out.println(login.getCode()+":"+login.getMsg());
	}

}
