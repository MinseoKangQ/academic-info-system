package com.hansung.enrollment.dto;

import com.hansung.enrollment.entity.CourseCategory;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class EnrollmentDto {

    @NotBlank(message = "과목 코드는 비어있을 수 없습니다.")
    private String courseCode;

    @NotBlank(message = "과목명은 비어있을 수 없습니다.")
    private String courseName;

    @NotNull(message = "과목 분류를 선택해야 합니다.")
    private CourseCategory courseCategory;

    @Min(value = 1, message = "1학점 이상이어야 합니다.")
    private int credits;
}