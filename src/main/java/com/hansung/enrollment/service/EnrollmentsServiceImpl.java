package com.hansung.enrollment.service;

import com.hansung.enrollment.dto.EnrollmentDto;
import com.hansung.enrollment.entity.Enrollments;
import com.hansung.enrollment.repository.EnrollmentsRepository;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollmentsServiceImpl implements EnrollmentsService {

    private final EnrollmentsRepository enrollmentsRepository;

    @Override
    public void registerEnrollment(EnrollmentDto dto) {
        enrollmentsRepository.findById(dto.getCourseCode()).ifPresent(enrollment -> {
            enrollment.changeRegisterStatus(true);
            enrollmentsRepository.save(enrollment);
        });
    }

    @Override
    public void cancelEnrollment(EnrollmentDto dto) {
        enrollmentsRepository.findById(dto.getCourseCode()).ifPresent(enrollment -> {
            enrollment.changeRegisterStatus(false);
            enrollmentsRepository.save(enrollment);
        });
    }

    @Override
    public List<Enrollments> findAllEnrollments() {
        return enrollmentsRepository.findAll();
    }

    @Override
    public List<Enrollments> findAllRegisteredEnrollments() {
        return enrollmentsRepository.findAll().stream()
                .filter(Enrollments::getRegistered)
                .collect(Collectors.toList());
    }

}
