package com.study.common.springbootv1.sample.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class StaticConfigService {
    @Value("${IMAGE_SERVER}")
    private String IMAGE_SERVER;

    public String getConfig() {
        return IMAGE_SERVER;
    }
}
