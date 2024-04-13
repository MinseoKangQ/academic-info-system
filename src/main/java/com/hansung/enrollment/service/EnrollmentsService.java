package com.hansung.enrollment.service;

import com.hansung.enrollment.dto.EnrollmentDto;
import com.hansung.enrollment.entity.Enrollments;
import java.util.List;

public interface EnrollmentsService {
    void registerEnrollment(EnrollmentDto dto);
    void cancelEnrollment(EnrollmentDto dto);
    List<Enrollments> findAllEnrollments();
    List<Enrollments> findAllRegisteredEnrollments();

}
