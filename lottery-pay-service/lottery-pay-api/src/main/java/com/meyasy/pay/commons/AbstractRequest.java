package com.meyasy.pay.commons;

public abstract class AbstractRequest {
    private static final long serialVersionUID = 1717442845820713651L;

    public abstract void requestCheck();

    @Override
    public String toString() {
        return "AbstractRequest{}";
    }
}
