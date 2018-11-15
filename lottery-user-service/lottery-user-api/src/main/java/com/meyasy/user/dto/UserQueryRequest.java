package com.meyasy.user.dto;


import com.meyasy.user.abs.AbstractRequest;


/**
 * 腾讯课堂搜索 咕泡学院
 * 加群获取视频：608583947
 * 风骚的Michael 老师
 */
public class UserQueryRequest extends AbstractRequest {

    private Integer uid;


    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }


}
