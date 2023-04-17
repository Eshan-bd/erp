package com.brainstation23.erp.controller.web;

import com.brainstation23.erp.mapper.UserMapper;
import com.brainstation23.erp.model.domain.MyUser;
import com.brainstation23.erp.model.dto.UpdateUserRequest;
import com.brainstation23.erp.persistence.entity.UserEntity;
import com.brainstation23.erp.service.MyUserDetailsService;
import com.brainstation23.erp.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Controller
@ControllerAdvice
@RequestMapping("profile")
public class ProfileInfoController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final MyUserDetailsService myUserDetailsService;

    @GetMapping
    public ModelAndView profile(Principal principal){
        log.info("Getting User Information.");

        var mav = new ModelAndView("profileInfo");
        var user = userService.getOneByUserName(principal.getName());

        mav.addObject("updateRequest", new UpdateUserRequest());
        mav.addObject("user", user);
        log.info(user.getId() + " logged in.");

        return mav;
    }

    @PostMapping
    public String updateProfile(Principal principal,
                                @ModelAttribute("updateRequest") UpdateUserRequest updateRequest){
        log.info("Updating Profile.");
        userService.updateOne(principal.getName(), updateRequest);

        return "redirect:/";
    }

}
