package com.hansung.enrollment.controller;

import com.hansung.enrollment.dto.EnrollmentDto;
import com.hansung.enrollment.service.EnrollmentsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class EnrollmentsController { // 수강 신청

    private final EnrollmentsService enrollmentsService;

    // 수강 신청 페이지
    @GetMapping("/enrollments")
    public String enroll(Model model, @AuthenticationPrincipal UserDetails currentUser) {
        if (currentUser != null) {
            model.addAttribute("username", currentUser.getUsername());
        }

        model.addAttribute("enrollments", enrollmentsService.findAllEnrollments());
        return "enrollments";
    }

    // 수강 신청
    @PostMapping("/enrollments/register")
    public String registerEnrollmentPost(@RequestBody @Valid EnrollmentDto dto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "enrollments";
        }
        enrollmentsService.registerEnrollment(dto);
        return "redirect:/enrollments";
    }

    // 수강 취소
    @PostMapping("/enrollments/cancel")
    public String cancelEnrollmentPost(@RequestBody @Valid EnrollmentDto dto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "enrollments";
        }
        enrollmentsService.cancelEnrollment(dto);
        return "redirect:/enrollments";
    }

    // 수강 신청 내역 페이지
    @GetMapping("/registered-enrollments")
    public String registeredEnrollments(Model model, @AuthenticationPrincipal UserDetails currentUser) {
        if (currentUser != null) {
            model.addAttribute("username", currentUser.getUsername());
        }

        model.addAttribute("enrollments", enrollmentsService.findAllRegisteredEnrollments());
        return "registered_enrollments";
    }
}