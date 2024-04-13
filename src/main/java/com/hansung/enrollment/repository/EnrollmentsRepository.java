package com.hansung.enrollment.repository;

import com.hansung.enrollment.entity.Enrollments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentsRepository extends JpaRepository<Enrollments, String> {
}
