package com.meyasy.user;

import com.meyasy.user.dto.*;

public interface IUserCoreService {
    /**
     * 登录方法
     * @param request
     * @return
     */
    UserLoginResponse login(UserLoginRequest request);

    /**
     * 校验token
     * @param request
     * @return
     */
    CheckAuthResponse validToken(CheckAuthRequest request);

    /**
     * 注册方法
     * @param request
     * @return
     */
    UserRegisterResponse register(UserRegisterRequest request);



}
