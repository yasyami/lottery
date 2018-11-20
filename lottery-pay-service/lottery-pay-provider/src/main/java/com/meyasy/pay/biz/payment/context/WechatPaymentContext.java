package com.meyasy.pay.biz.payment.context;

import com.meyasy.pay.biz.abs.PaymentContext;
import org.springframework.stereotype.Service;

/**
 * 腾讯课堂搜索 咕泡学院
 * 加群获取视频：608583947
 * 风骚的Michael 老师
 * 微信支付的上下文信息
 */
@Service
public class WechatPaymentContext extends PaymentContext {
    private String body; //商品描述（必填）
    private String spbillCreateIp; //终端IP

    /**
     * JSAPI--公众号支付
     * NATIVE--原生扫码支付
     * APP--app支付，统一下单接口trade_type的传参可参考这里
     * MICROPAY--刷卡支付，刷卡支付有单独的支付接口，不调用统一下单接口
     */
    private String tradeType;//交易类型（必填）

    /**
     * 商品id
     */
    private String productId;

    private String xml; //拼接的xml格式数据，用于传递给微信服务端的参数

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSpbillCreateIp() {
        return spbillCreateIp;
    }

    public void setSpbillCreateIp(String spbillCreateIp) {
        this.spbillCreateIp = spbillCreateIp;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }
}
