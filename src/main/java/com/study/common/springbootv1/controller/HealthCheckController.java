package com.study.common.springbootv1.controller;

import com.study.common.springbootv1.domain.response.CommonResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/healthcheck")
@RestController
public class HealthCheckController {
    @GetMapping("/_check/{name}")
    public CommonResponse getString(@PathVariable String name) {
        return CommonResponse.builder()
                .code("0000")
                .message("Success")
                .data(new HealthCheckDTO("OK"))
                .build();
    }

    @Data
    @AllArgsConstructor
    private class HealthCheckDTO {
        private String status;
    }
}
