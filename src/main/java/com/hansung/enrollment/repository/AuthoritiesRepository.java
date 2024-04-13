package com.hansung.enrollment.repository;

import com.hansung.enrollment.entity.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthoritiesRepository extends JpaRepository<Authorities, String> {
}