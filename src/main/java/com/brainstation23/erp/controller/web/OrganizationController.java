package com.brainstation23.erp.controller.web;

import com.brainstation23.erp.mapper.OrganizationMapper;
import com.brainstation23.erp.model.dto.CreateOrganizationRequest;
import com.brainstation23.erp.model.dto.UpdateOrganizationRequest;
import com.brainstation23.erp.service.OrganizationService;
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
@RequestMapping("organizations")
public class OrganizationController {

    private final OrganizationService organizationService;
    private final OrganizationMapper organizationMapper;

    @GetMapping
    public String organization(Model model) {

        log.info("Getting List of Organizations");
        var domains = organizationService.getAll(PageRequest.of(0, 20));
        model.addAttribute("organizationList", domains);

        var response = ResponseEntity.ok(domains.map(organizationMapper::domainToResponse));
        if (response.getStatusCode().isError()) {
            return response.toString();
        }
        return "organization/organizations";
    }

    @GetMapping("/create")
    public ModelAndView createOrganizationView() {

        return new ModelAndView(
                "organization/createOrganization",
                "createRequest",
                new CreateOrganizationRequest()
        );
    }

    @PostMapping("/create")
    public String createOne(@ModelAttribute CreateOrganizationRequest createRequest,
                            BindingResult result) {

        if (result.hasErrors()) {
            return result.getAllErrors().toString();
        }
        log.info("Creating an Organization: {} ", createRequest);
        organizationService.createOne(createRequest);

        return "redirect:/organizations?created";
    }

    @GetMapping("/update/{orgId}")
    public ModelAndView updateOrganizationView(Model model, @PathVariable UUID orgId) {

        model.addAttribute("orgId", orgId);

        return new ModelAndView("organization/updateOrganization",
                "updateRequest",
                new UpdateOrganizationRequest()
        );
    }

    @PostMapping("/update/{orgId}")
    public String updateOne(@PathVariable UUID orgId,
                            @ModelAttribute("updateRequest") UpdateOrganizationRequest updateRequest) {

        log.info("initialized id" + orgId);
        log.info("Updating an Organization({}): {} ", orgId, updateRequest);
        organizationService.updateOne(orgId, updateRequest);

        return "redirect:/organizations?updated";
    }

    @GetMapping("/delete/{orgId}")
    public String deleteOrganizationView(Model model, @PathVariable UUID orgId) {
        model.addAttribute("orgId", orgId);
        return "organization/deleteOrganization";
    }

    @PostMapping("/delete/{orgId}")
    public String deleteOne(@PathVariable UUID orgId) {
        log.info("Deleting an Organization({}) ", orgId);
        organizationService.deleteOne(orgId);
        return "redirect:/organizations?deleted";
    }
}

