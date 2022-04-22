package com.study.common.springbootv1.controller;


import com.study.common.springbootv1.domain.entity.UserEntity;
import com.study.common.springbootv1.repository.user.UserRepository;
import com.study.common.springbootv1.service.DynamicConfigService;
import com.study.common.springbootv1.service.StaticConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/view")
@RequiredArgsConstructor
public class ThymeleafController {
    private final StaticConfigService staticConfigService;
    private final DynamicConfigService dynamicConfigService;

    private final UserRepository userRepository;

    @GetMapping("/test")
    public ModelAndView testView(ModelAndView mv) {

        String staticConfig = staticConfigService.getConfig();
        String dynamicConfig = dynamicConfigService.getConfig();
        mv.addObject("staticConfig", staticConfig);
        mv.addObject("dynamicConfig", dynamicConfig);
        mv.setViewName("test");
        return mv;
    }

    @GetMapping("/users")
    public ModelAndView getUsers(ModelAndView mv) {
        List<UserEntity> allUser = userRepository.findAll();
        mv.addObject("title","This is");
        mv.addObject("msg", "Detail msg");
        mv.addObject("allUser", allUser);
        mv.setViewName("user");
        return mv;
    }
}
