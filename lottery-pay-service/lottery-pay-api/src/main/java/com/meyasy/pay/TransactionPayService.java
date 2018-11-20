package com.meyasy.pay;

import com.meyasy.pay.dto.PaymentNotifyRequest;
import com.meyasy.pay.dto.PaymentNotifyResponse;
import com.meyasy.pay.dto.PaymentRequest;
import com.meyasy.pay.dto.PaymentResponse;

/**
 * 腾讯课堂搜索 咕泡学院
 * 加群获取视频：608583947
 * 风骚的Michael 老师
 */
public interface TransactionPayService {

    /**
     * 执行支付操作
     * @param request
     * @return
     */
    PaymentResponse execPay(PaymentRequest request);


    /**
     * 支付结果通知处理
     * @param request
     * @return
     */
    PaymentNotifyResponse paymentResultNotify(PaymentNotifyRequest request);
}
