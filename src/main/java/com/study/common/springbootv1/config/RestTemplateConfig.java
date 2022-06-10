package com.study.common.springbootv1.config;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        // 기본적으로 restTemplate 는 커넥션 풀을 가지고 있지 않다.
        // 호출할 때마다 로컬에서 임시 TCP 소켓을 개방하여 사용한다.
        // 이 때, 요청이 많아지면 TIME_WAIT 상태의 커넥션은 재사용이 불가능하므로 성능 저하가 발생한다.
        // 단, 호출하는 서버가 Keep-alive 를 지원 해줘야 의미 있느 설정이 된다.
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                                                          .setMaxConnTotal(120) //연결을 유지할 최대 숫자
                                                          .setMaxConnPerRoute(100) //특정 경로당 최대 숫자
                                                          .setConnectionTimeToLive(5, TimeUnit.SECONDS) // keep - alive
                                                          .build();

        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setHttpClient(httpClient);

        //인터셉터가 요청 / 응답 로거로서 기능하도록하려면 인터셉터가 처음으로, 클라이언트가 두 번째로 두 번 읽어야한다.
        //기본 구현에서는 응답 스트림을 한 번만 읽을 수 있습니다.
        // 이러한 특정 시나리오를 제공하기 위해 Spring은 BufferingClientHttpRequestFactory 라는 특수 클래스를 제공.
        // 이름에서 알 수 있듯이 이 클래스는 여러 용도로 JVM 메모리에서 요청 / 응답을 버퍼링합니다.
        BufferingClientHttpRequestFactory bufferingClientHttpRequestFactory = new BufferingClientHttpRequestFactory(factory);

        RestTemplate restTemplate = restTemplateBuilder
            .requestFactory(() -> bufferingClientHttpRequestFactory)
            .setConnectTimeout(Duration.ofMillis(5000)) //읽기시간초과, ms
            .setReadTimeout(Duration.ofMillis(5000))    //연결시간초과, ms
            .build();

        // restTemplate 의 interceptor 를 얻어서 header 에 content-type 값을 추가한다.
        List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
        if (CollectionUtils.isEmpty(interceptors)) {
            interceptors = new ArrayList<>();
        }
        interceptors.add((request, body, execution) -> {
            ClientHttpResponse response = execution.execute(request,body);
            response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
            return response;
        });
        restTemplate.setInterceptors(interceptors);

        return restTemplate;
    }
}

