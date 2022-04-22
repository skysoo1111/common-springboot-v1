package com.study.common.springbootv1.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

@Service
@RefreshScope
public class DynamicConfigService {
    @Value("${IMAGE_SERVER}")
    private String IMAGE_SERVER;

    public String getConfig() {
        return IMAGE_SERVER;
    }
}
