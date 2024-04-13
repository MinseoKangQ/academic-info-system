package com.hansung.enrollment.controller;

import com.hansung.enrollment.entity.Courses;
import com.hansung.enrollment.service.CoursesService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class CreditsController { // 수강 내역

    private final CoursesService coursesService;

    // 학기별 수강 정보 페이지
    @GetMapping("/credits")
    public String credits(Model model, @AuthenticationPrincipal UserDetails currentUser) {
        if (currentUser != null) {
            model.addAttribute("username", currentUser.getUsername());
        }

        List<Object[]> creditsPerSemester = coursesService.findCreditsPerSemester();
        model.addAttribute("creditsPerSemester", creditsPerSemester);

        int totalCredits = coursesService.calculateTotalCredits(creditsPerSemester);
        model.addAttribute("totalCredits", totalCredits);

        return "credits";
    }

    // 학기별 수강 정보 상세 조회
    @RequestMapping(value = "/detail-credits", method = RequestMethod.GET)
    @ResponseBody
    public List<Courses> getDetailCredits(@RequestParam("year") int year, @RequestParam("semester") String semester) {
        return coursesService.findByYearAndSemester(year, semester);
    }
}
