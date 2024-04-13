package com.hansung.enrollment.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // 홈 페이지
    @GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal UserDetails currentUser) {
        if (currentUser != null) {
            model.addAttribute("username", currentUser.getUsername());
        }
        return "home";
    }


}