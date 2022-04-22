package com.study.common.springbootv1.handler;

import java.io.Serializable;

public class CommonException extends Exception implements Serializable {
    private static final long serialVersionUID = 7141448969078998912L;

    public CommonException(String exceptionStr) {
        super(exceptionStr);
    }
}
