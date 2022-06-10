package com.study.common.springbootv1.utility;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public final class Globals {

    public static String API_URL;

    @Value("${API_URL}")
    private void setApiUrl(String apiUrl) {
        API_URL = apiUrl;
    }
}
