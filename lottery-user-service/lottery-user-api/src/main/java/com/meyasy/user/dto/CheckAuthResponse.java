package com.meyasy.user.dto;

import com.meyasy.user.abs.AbstractResponse;

public class CheckAuthResponse extends AbstractResponse{

    private String uid;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "CheckAuthResponse{" +
                "uid='" + uid + '\'' +
                '}';
    }
}
