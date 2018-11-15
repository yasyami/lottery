package com.meyasy.user.abs;

import java.io.Serializable;

public abstract class AbstractResponse implements Serializable{
    private static final long serialVersionUID = -246764203436409958L;

    private String msg;

    private String code;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
