package com.meyasy.user.services;

import com.alibaba.dubbo.config.annotation.Service;
import com.meyasy.exception.ExceptionUtil;
import com.meyasy.exception.ValidateException;
import com.meyasy.user.IUserQueryService;
import com.meyasy.user.constants.ResponseCodeEnum;
import com.meyasy.user.dto.UserQueryRequest;
import com.meyasy.user.dto.UserQueryResponse;
import com.meyasy.user.entity.User;
import com.meyasy.user.persistence.dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class UserQueryServiceImpl implements IUserQueryService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserDao userDao;

    @Override
    public UserQueryResponse getUserById(UserQueryRequest request) {
        UserQueryResponse response = new UserQueryResponse();
        try{
            validateBeforeQuery(request);
            User user = userDao.getUserByUid(request.getUid());
            if(user!=null){
                response.setMsg(ResponseCodeEnum.SUCCESS.getMsg());
                response.setCode(ResponseCodeEnum.SUCCESS.getCode());
                response.setAvatar(user.getAvatar());
                response.setMobile(user.getMobile());
                response.setRealName(user.getRealname());
                response.setSex(user.getSex());
                return response;
            }
            response.setCode(ResponseCodeEnum.SYSTEM_BUSY.getCode());
            response.setMsg(ResponseCodeEnum.SYSTEM_BUSY.getMsg());
            return response;
        }catch (Exception e){
            ValidateException validateException = (ValidateException) ExceptionUtil.handleException4Biz(e);
            response.setCode(validateException.getErrorCode());
            response.setMsg(validateException.getErrorMessage());
        }finally{
            logger.info("response:"+response);
        }
        return response;
    }

    private void validateBeforeQuery(UserQueryRequest request) {
        if(request==null){
            throw new ValidateException("请求对象为空");
        }
        if (request.getUid()==null||request.getUid().intValue()==0){
            throw new ValidateException("请求UID为空");
        }
    }
}
