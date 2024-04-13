package com.hansung.enrollment.service;

import com.hansung.enrollment.entity.Courses;
import com.hansung.enrollment.repository.CoursesRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CoursesServiceImpl implements CoursesService {

    private final CoursesRepository coursesRepository;

    @Override
    public List<Object[]> findCreditsPerSemester() {
        return coursesRepository.findCreditsPerSemester();
    }

    @Override
    public List<Courses> findByYearAndSemester(int year, String semester) {
        return coursesRepository.findByYearAndSemester(year, semester);
    }

    @Override
    public int calculateTotalCredits(List<Object[]> creditsPerSemester) {
        int totalCredits = 0;
        for (Object[] semesterCredits : creditsPerSemester) {
            totalCredits += ((Number) semesterCredits[2]).intValue();
        }
        return totalCredits;
    }

}