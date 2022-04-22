package com.study.common.springbootv1.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/v2/api-docs", "/swagger-resources/**",
        "/swagger-ui.html", "/swagger-ui/**", "/webjars/**", "/swagger/**", "/docs/**");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .httpBasic().disable() // rest api 이므로 기본설정 사용안함. 기본설정은 비인증시 로그인폼 화면으로 리다이렉트 된다.
        .csrf().disable() // rest api이므로 csrf 보안이 필요없으므로 disable처리.
        .authorizeRequests() // 다음 리퀘스트에 대한 사용권한 체크
        .antMatchers("/v1/**", // Controller 관련
                "/view/**").permitAll()
        .antMatchers(HttpMethod.GET, "/exception/**","main-boards/**", "/actuator/health", "/docs/**", "/healthcheck/**").permitAll(); // 등록된 GET요청 리소스는 누구나 접근가능
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }
}
