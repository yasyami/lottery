package com.meyasy.user.services;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.meyasy.exception.ExceptionUtil;
import com.meyasy.exception.ServiceException;
import com.meyasy.exception.ValidateException;
import com.meyasy.user.IUserCoreService;
import com.meyasy.user.constants.Constants;
import com.meyasy.user.constants.ResponseCodeEnum;
import com.meyasy.user.dto.*;
import com.meyasy.user.entity.User;
import com.meyasy.user.persistence.dao.UserDao;
import com.meyasy.utils.JwtTokenUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserCoreServiceImpl implements IUserCoreService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserDao userDao;

    @Override
    public UserLoginResponse login(UserLoginRequest request) {
        UserLoginResponse response = new UserLoginResponse();
        try {
            beforeValidate(request);
            User user = userDao.getUserByUserName(request.getUserName());
            //校验登录
            if (user == null || !request.getPassword().equals(user.getPassword())) {
                response.setCode(ResponseCodeEnum.USERORPASSWORD_ERROR.getCode());
                response.setMsg(ResponseCodeEnum.USERORPASSWORD_ERROR.getMsg());
                return response;
            }
            //存储token信息
            Map<String, Object> map = new HashMap<>();
            map.put("uid", "");
            map.put("exp", DateTime.now().plusDays(1).toDate().getTime() / 1000);
            response.setToken(JwtTokenUtils.generatorToken(map));
            response.setAvatar(user.getAvatar());
            response.setMobile(user.getMobile());
            response.setUid(user.getId());
            response.setMsg(ResponseCodeEnum.SUCCESS.getCode());
            response.setCode(ResponseCodeEnum.SUCCESS.getMsg());
        } catch (Exception e) {
            logger.error("login occur error:" + e);
            ServiceException serviceException = (ServiceException) ExceptionUtil.handleException4Biz(e);
            response.setCode(serviceException.getErrorCode());
            response.setMsg(serviceException.getErrorMessage());
        } finally {
            logger.info("login response:" + response);
        }
        return response;
    }

    @Override
    public CheckAuthResponse validToken(CheckAuthRequest request) {
        CheckAuthResponse response = new CheckAuthResponse();
        try {
            beforeValidateAuth(request);
            Claims claims = JwtTokenUtils.phaseToken(request.getToken());
            response.setUid(claims.get("uid").toString());
            response.setCode(ResponseCodeEnum.SUCCESS.getCode());
            response.setMsg(ResponseCodeEnum.SUCCESS.getMsg());
            return response;
        } catch (ExpiredJwtException e) {//过期异常
            logger.error("expired:" + e);
            response.setCode(ResponseCodeEnum.TOKEN_EXPIRE.getCode());
            response.setCode(ResponseCodeEnum.TOKEN_EXPIRE.getMsg());
        } catch (SignatureException e) {
            logger.error("signature:" + e);
            response.setCode(ResponseCodeEnum.SIGNATURE_ERROR.getCode());
            response.setCode(ResponseCodeEnum.SIGNATURE_ERROR.getMsg());
        } catch (Exception e) {
            logger.error("error:" + e);
            ValidateException validateException = (ValidateException) ExceptionUtil.handleException4Biz(e);
            response.setCode(validateException.getErrorCode());
            response.setMsg(validateException.getErrorMessage());
        } finally {
            logger.info("response:" + response);
        }

        return response;
    }

    @Override
    public UserRegisterResponse register(UserRegisterRequest request) {
        UserRegisterResponse response = new UserRegisterResponse();
        try {
            validateBeforeRegister(request);
            User user = new User();
            user.setUsername(request.getUsername());
            user.setPassword(request.getPassword());
            user.setCreateTime(DateTime.now().toDate());
            user.setSex(request.getSex());
            user.setStatus(Constants.NORMAL_USER_STATUS);
            int i = userDao.insertSelective(user);
            if (i > 0) {
                response.setCode(ResponseCodeEnum.SUCCESS.getCode());
                response.setMsg(ResponseCodeEnum.SUCCESS.getMsg());
                return response;
            }
            response.setCode(ResponseCodeEnum.SYSTEM_BUSY.getCode());
            response.setMsg(ResponseCodeEnum.SYSTEM_BUSY.getMsg());
            return response;
        } catch (DuplicateKeyException e) {
            //TODO 主键重复
        } catch (Exception e) {
            ValidateException validateException = (ValidateException) ExceptionUtil.handleException4Biz(e);
            response.setCode(validateException.getErrorCode());
            response.setMsg(validateException.getErrorMessage());
        } finally {
            logger.info("response:" + response);
        }

        return response;
    }

    private void validateBeforeRegister(UserRegisterRequest request) {
        if (request == null) {
            throw new ValidateException("请求对象为空");
        }
        if (StringUtils.isEmpty(request.getMobile())) {
            throw new ValidateException("电话为空");
        }
        if (StringUtils.isEmpty(request.getPassword())) {
            throw new ValidateException("用户密码为空");
        }
        if (StringUtils.isEmpty(request.getUsername())) {
            throw new ValidateException("用户名为空");
        }

    }


    private void beforeValidateAuth(CheckAuthRequest request) {
        if (request == null) {
            throw new ValidateException("请求对象对空");
        }
        if (StringUtils.isEmpty(request.getToken())) {
            throw new ValidateException("token为空");
        }

    }


    private void beforeValidate(UserLoginRequest request) {
        if (request == null) {
            throw new ValidateException("请求对象为空");
        }
        if (StringUtils.isEmpty(request.getUserName())) {
            throw new ValidateException("用户名为空");
        }
        if (StringUtils.isEmpty(request.getPassword())) {
            throw new ValidateException("密码为空");
        }

    }
}
