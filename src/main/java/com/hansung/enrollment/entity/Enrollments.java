package com.hansung.enrollment.entity;

import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "ENROLLMENTS")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Enrollments {

    @Id
    @NotBlank(message = "과목코드는 비어있을 수 없습니다.")
    private String courseCode;

    @Builder.Default
    private int year = 2024;

    @Builder.Default
    private String semester = "2";

    @NotBlank(message = "과목명은 비어있을 수 없습니다.")
    private String courseName;

    @Enumerated(EnumType.STRING)
    private CourseCategory courseCategory;

    @NotBlank(message = "교수명은 비어있을 수 없습니다.")
    private String professor;

    @Min(value = 1, message = "1학점 이상이어야 합니다.")
    private int credits;

    @Builder.Default
    private Boolean registered = false;

    public void changeRegisterStatus(Boolean registered) {
        this.registered = registered;
    }
}
