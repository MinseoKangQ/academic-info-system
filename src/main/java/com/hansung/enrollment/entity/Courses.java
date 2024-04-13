package com.hansung.enrollment.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "COURSES")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Courses {

    @Id
    private String courseCode; // 교과코드

    private int year; // 수강년도
    private String semester; // 학기
    private String courseName; // 교과목명

    @Enumerated(EnumType.STRING)
    private CourseCategory courseCategory; // 교과구분

    private String professor; // 담당교수
    private int credits; // 학점
}
