package com.brainstation23.erp.controller.web;

import com.brainstation23.erp.mapper.UserMapper;
import com.brainstation23.erp.model.dto.CreateUserRequest;
import com.brainstation23.erp.model.dto.UpdateUserRequest;
import com.brainstation23.erp.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Controller
@ControllerAdvice
@RequestMapping("users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    public String users(Model model){

        log.info("Getting List of users");
        var domains = userService.getAll(PageRequest.of(0, 20));
        model.addAttribute("userList", domains);

        var response = ResponseEntity.ok(domains.map(userMapper::domainToResponse));
        if (response.getStatusCode().isError()){
            return response.toString();
        }
        return "user/users";
    }

    @GetMapping("/create")
    public ModelAndView createUserView(){

        return new ModelAndView(
                "user/createUser",
                "createRequest",
                new CreateUserRequest()
        );
    }

    @PostMapping("/create")
    public String createOne(@ModelAttribute CreateUserRequest createRequest,
                            BindingResult result) {

        if (result.hasErrors()){
            return result.getAllErrors().toString();
        }
        log.info("Creating a user: {} ", createRequest);
        userService.createOne(createRequest);

        return "redirect:/users?created";
    }

    @GetMapping("/update/{id}")
    public ModelAndView updateUserView(Model model, @PathVariable UUID id){

        model.addAttribute("id", id);
        model.addAttribute("userBalance", userService.getBalanceById(id));

        return new ModelAndView("user/updateUser",
                "updateRequest",
                new UpdateUserRequest()
        );
    }

    @PostMapping("/update/{id}")
    public String updateOne(@PathVariable UUID id,
                            @ModelAttribute("updateRequest") UpdateUserRequest updateRequest){

        log.info("initialized id" + id);
        log.info("Updating a user({}): {} ", id, updateRequest);

        userService.updateOne(id, updateRequest);

        return "redirect:/users?updated";
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteUserView(Model model, @PathVariable UUID id){
        model.addAttribute("id", id);
        return new ModelAndView(
                "user/deleteUser",
                "myUser",
                userService.getOne(id)
        );

    }

    @PostMapping("/delete/{id}")
    public String deleteOne(@PathVariable UUID id) {
        log.info("Deleting a user({}) ", id);
        userService.deleteOne(id);
        return "redirect:/users?deleted";
    }
}

