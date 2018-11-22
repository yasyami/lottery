package com.meyasy.pay.biz.abs;


import com.meyasy.pay.commons.AbstractRequest;

/**
 * 数据验证接口类
 */
public interface Validator {

    void validate(AbstractRequest request);
}
