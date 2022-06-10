package com.study.common.springbootv1.service;

import com.study.common.springbootv1.domain.Channel;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChannelService {
    private final RestTemplate restTemplate;

    public List<Channel> callPipApiList(URI uri) {
        ResponseEntity<List<Channel>> result = restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<>() {});
        return result.getBody();
    }

    // TODO: 2022-06-08 DB 연결 로직
}
