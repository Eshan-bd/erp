package com.brainstation23.erp.controller.web;

import com.brainstation23.erp.constant.ROLE;
import com.brainstation23.erp.model.dto.CreateUserRequest;
import com.brainstation23.erp.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/signup")
public class SignupController {
    private final UserService userService;

    @GetMapping
    public ModelAndView signupView() {

        return new ModelAndView(
                "signup",
                "createRequest",
                new CreateUserRequest()
        );
    }

    @PostMapping
    public String createOne(@ModelAttribute CreateUserRequest createRequest,
                            BindingResult result) {

        if (result.hasErrors()){
            return result.getAllErrors().toString();
        }
        createRequest.setRole(ROLE.GUEST);
        log.info("A user is signing up: {} ", createRequest);
        userService.createOne(createRequest);

        return "redirect:?signedup";
    }
}

