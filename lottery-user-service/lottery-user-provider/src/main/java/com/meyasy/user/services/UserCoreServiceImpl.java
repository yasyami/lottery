package com.meyasy.user.services;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.meyasy.user.IUserCoreService;
import com.meyasy.user.constants.ResponseCodeEnum;
import com.meyasy.user.dto.*;
import com.meyasy.user.entity.User;
import com.meyasy.user.exception.ExceptionUtil;
import com.meyasy.user.exception.ServiceException;
import com.meyasy.user.exception.ValidateException;
import com.meyasy.user.persistence.dao.UserDao;
import com.meyasy.user.utils.JwtTokenUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

@Service(version = "${demo.service.version}",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}")
public class UserCoreServiceImpl implements IUserCoreService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserDao userDao;

    @Override
    public UserLoginResponse login(UserLoginRequest request) {
        UserLoginResponse response = new UserLoginResponse();
        try{
            beforeValidate(request);
            User user = userDao.getUserByUserName(request.getUserName());
            //校验登录
            if(user==null||!request.getPassword().equals(user.getPassword())){
                response.setCode(ResponseCodeEnum.USERORPASSWORD_ERROR.getCode());
                response.setMsg(ResponseCodeEnum.USERORPASSWORD_ERROR.getMsg());
                return response;
            }
            //存储token信息
            Map<String,Object> map  = new HashMap<>();
            map.put("uid","");
            map.put("exp", DateTime.now().plusDays(1).toDate().getTime()/1000);
            response.setToken(JwtTokenUtils.generatorToken(map));
            response.setAvatar(user.getAvatar());
            response.setMobile(user.getMobile());
            response.setUid(user.getId());
            response.setMsg(ResponseCodeEnum.SUCCESS.getCode());
            response.setCode(ResponseCodeEnum.SUCCESS.getMsg());
        }catch (Exception e){
            logger.error("login occur error:"+e);
            ServiceException serviceException = (ServiceException) ExceptionUtil.handleException4Biz(e);
            response.setCode(serviceException.getErrorCode());
            response.setMsg(serviceException.getErrorMessage());
        }finally {
            logger.info("login response:"+response);
        }
        return response;
    }

    @Override
    public CheckAuthResponse validToken(CheckAuthRequest request) {
        return null;
    }

    @Override
    public UserRegisterResponse register(UserLoginRequest request) {
        return null;
    }

    private void beforeValidate(UserLoginRequest request) {
        if(request==null){
            throw new ValidateException("请求对象为空");
        }
        if(StringUtils.isEmpty(request.getUserName())){
            throw new ValidateException("用户名为空");
        }
        if(StringUtils.isEmpty(request.getPassword())){
            throw new ValidateException("密码为空");
        }

    }
}
