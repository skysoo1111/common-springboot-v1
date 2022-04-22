package com.study.common.springbootv1.controller;

import com.study.common.springbootv1.domain.User;
import com.study.common.springbootv1.domain.entity.UserEntity;
import com.study.common.springbootv1.domain.response.CommonResponse;
import com.study.common.springbootv1.handler.CommonException;
import com.study.common.springbootv1.handler.UserNotFoundException;
import com.study.common.springbootv1.mapper.UserMapper;
import com.study.common.springbootv1.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class UserController {

    // JPA
    private final UserRepository userRepository;
    // Mybatis
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/signin")
    public User signin(@RequestParam String uid,
                       @RequestParam String password,
                       HttpServletRequest request) throws CommonException {
        User user = userMapper.findByUid(uid);

        if (!passwordEncoder.matches(password, user.getPassword()))
            throw new CommonException("PASSWORD NOT MATCHES");

        log.info("##### user.getId() : {}", user.getId());
        return user;
    }

    @PostMapping("/signup")
    public CommonResponse signup(@RequestBody UserEntity singupUser,
                                 HttpServletRequest request) {
        try {
            userRepository.save(singupUser);
            User savedUser = User.builder()
                    .id(singupUser.getId())
                    .uid(singupUser.getUid())
                    .name(singupUser.getName())
                    .password(singupUser.getPassword())
                    .build();
            return CommonResponse.builder().code("0000").message("Success").data(savedUser).build();
        } catch (Exception e){
            return CommonResponse.builder().code("9999").message("Fail").data(singupUser).build();
        }
    }

    @GetMapping("/mybatis/member/{email}")
    public CommonResponse getMemberByUid(@PathVariable String email) {
        User byUid = userMapper.findByUid(email);
        if (byUid == null)
            return CommonResponse.builder().code("9999").message("Fail").data(null).build();

        return CommonResponse.builder()
                .code("0000")
                .message("Success")
                .data(byUid)
                .build();
    }

    @GetMapping("/jpa/member1/{email}")
    public CommonResponse getMemberEntityByUid(@PathVariable String email) {
        UserEntity entityByUid = userRepository.getMemberByUid(email);

        if (entityByUid == null)
            return CommonResponse.builder().code("9999").message("Fail").data(null).build();

        return CommonResponse.builder()
                .code("0000")
                .message("Success")
                .data(entityByUid)
                .build();
    }

    @GetMapping("/jpa/member2/{id}")
    public CommonResponse getMemberEntityByUid(@PathVariable Long id) {
        UserEntity userEntity = userRepository.getById(id);
        Optional.of(userEntity).orElseThrow(() -> new UserNotFoundException(id));

        return CommonResponse.builder()
                .code("0000")
                .message("Success")
                .data(userEntity)
                .build();
    }

    @DeleteMapping("/member/{id}")
    public void deleteMemberById(@PathVariable Long id) {
        userRepository.deleteById(id);
    }
}
