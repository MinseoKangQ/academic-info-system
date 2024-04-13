package com.hansung.enrollment.service;

import com.hansung.enrollment.entity.Courses;
import java.util.List;

public interface CoursesService {
    List<Object[]> findCreditsPerSemester();
    List<Courses> findByYearAndSemester(int year, String semester);
    int calculateTotalCredits(List<Object[]> creditsPerSemester);
}
