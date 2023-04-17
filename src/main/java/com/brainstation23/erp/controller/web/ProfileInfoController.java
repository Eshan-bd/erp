package com.brainstation23.erp.controller.web;

import com.brainstation23.erp.mapper.UserMapper;
import com.brainstation23.erp.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RequiredArgsConstructor
@Controller
@ControllerAdvice
@RequestMapping("profile")
public class ProfileInfoController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    public ModelAndView profile(){
        log.info("Getting User Information.");
        return new ModelAndView(
                "profileInfo"
        );
    }

}
