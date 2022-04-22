package com.study.common.springbootv1.handler;

import java.io.Serializable;

public class UserNotFoundException extends IllegalArgumentException implements Serializable {
    private static final long serialVersionUID = -6819779178909559171L;

    public UserNotFoundException (Long id) {
        super(String.format("User with Id %d not found", id));
    }
}
