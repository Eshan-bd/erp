package com.brainstation23.erp.controller.web;

import com.brainstation23.erp.constant.ROLE;
import com.brainstation23.erp.mapper.UserMapper;
import com.brainstation23.erp.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
@ControllerAdvice
public class EmployeesController {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/employees")
    public String employees(Model model){

        log.info("Getting List of employees");
        var domains = userService.getAllByRole(PageRequest.of(0, 20), ROLE.EMPLOYEE);
        model.addAttribute("employeeList", domains);

        var response = ResponseEntity.ok(domains.map(userMapper::domainToResponse));
        if (response.getStatusCode().isError()){
            return response.toString();
        }
        return "employees";
    }
}
