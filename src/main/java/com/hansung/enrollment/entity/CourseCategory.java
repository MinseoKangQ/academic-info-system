package com.hansung.enrollment.entity;

public enum CourseCategory {
    필수교양("필수교양"),
    선택필수교양("선택필수교양"),
    전공필수("전공필수"),
    전공선택("전공선택"),
    일반교양("일반교양");

    private final String description;

    CourseCategory(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}