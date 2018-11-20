package com.meyasy.exception;

import com.meyasy.user.constants.ResponseCodeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExceptionUtil {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionUtil.class);



    public static Exception handleException4Biz(Exception e){
        Exception ex = null;

        if(!(e instanceof Exception)){
            return null;
        }
        if (e instanceof ValidateException) {
            ex = new ServiceException(((ValidateException) e).getErrorCode(), ((ValidateException) e).getErrorMessage());
        }else if (e instanceof Exception) {
            ex = new ServiceException(ResponseCodeEnum.SYSTEM_BUSY.getCode(),
                    ResponseCodeEnum.SYSTEM_BUSY.getMsg());
        }
        logger.error("ExceptionUtil.handlerException4biz,Exception=" + e.getMessage(), e);
        return ex;

    }

}
