# common-springboot-v1
SpringBoot 2.6.4 + Gradle 7.4.1 환경에서의 기본 개발 환경 구성

- [x] Spring Cloud Config 설정
- [x] JPA + Mybatis 동시 사용 설정
- [x] QueryDSL 셋팅
- [x] API Docs (rest docs + openapi3) 설정
- [x] RestTemplate 설정
- [x] Thymeleaf 설정
- [x] 공통 응답 처리 로직 구현 (common-web jar import)
    - [common-web jar 파일 소스](https://github.com/skysoo1111/common-web.git)
    - [x] Health Check
    - [x] Exception Handler 구현
    - [x] ResponseBodyAdvice 공통 Response 구현

> 사전 준비 
- Mysql DB
- [Spring Cloud Config 서버](https://github.com/skysoo1111/spring-config-server) 

## 기동 CMD
> ./gradlew clean bootRun SPRING_PROFILES_ACTIVE=<PROFILE명>

## health check
~~~http request
GET http://localhost:8080/healthcheck/_check

HTTP/1.1 200 
{
  "code": "0000",
  "message": "Success",
  "data": "_check"
}
~~~

# 버전 정보
|항목|버전|
|---|---|
|JDK|11|
|Gradle|7.4.1|
|SpringBoot|2.6.4|
|Openfeign|3.1.1|
|QueryDsl|5.0.0|
|Hibernate|5.6.5|
|Mybatis|3.5.9|
|Mysql|8.0.28|
|Thymeleaf|3.0.15|
|Rest Docs|0.11.5|
|Lombok|1.18.22|


