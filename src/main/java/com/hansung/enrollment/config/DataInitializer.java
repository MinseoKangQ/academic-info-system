package com.hansung.enrollment.config;

import com.hansung.enrollment.entity.Enrollments;
import com.hansung.enrollment.repository.AuthoritiesRepository;
import com.hansung.enrollment.repository.CoursesRepository;
import com.hansung.enrollment.repository.EnrollmentsRepository;
import com.hansung.enrollment.repository.UsersRepository;
import com.hansung.enrollment.entity.CourseCategory;
import com.hansung.enrollment.entity.Courses;
import com.hansung.enrollment.entity.Users;
import com.hansung.enrollment.entity.Authorities;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final UsersRepository usersRepository;
    private final CoursesRepository coursesRepository;
    private final EnrollmentsRepository enrollmentsRepository;
    private final AuthoritiesRepository authoritiesRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @PostConstruct
    public void initData() {

        initUser();
        initCourses();
        initEnrollments();

    }

    // 사용자 생성
    private void initUser() {

        // 사용자 이름과 원본 비밀번호 설정
        String username = "강민서";
        String email = "kms2171344@hansung.ac.kr";
        String rawPassword = "password";

        // 비밀번호 인코딩
        String encodedPassword = bCryptPasswordEncoder.encode(rawPassword);

        // 새 Users 엔티티 생성 및 저장
        Users user = Users.builder()
                .username(username)
                .email(email)
                .password(encodedPassword)
                .build();

        usersRepository.save(user);

        // 새 Authorities 엔티티 생성 및 저장
        Authorities authority = new Authorities(username, "ROLE_USER");
        authoritiesRepository.save(authority);

        System.out.println("사용자와 권한 생성 완료");
    }

    // 수강 정보 생성
    void initCourses() {
        // 2021 - 1
        createCourse("REQ0018", 2021, "1", "문제해결을 위한 코딩 첫 걸음", CourseCategory.필수교양, "정성훈", 2);
        createCourse("REQ0020", 2021, "1", "사고와 표현(읽기와 쓰기)", CourseCategory.필수교양, "정원채", 2);
        createCourse("REQ0313", 2021, "1", "영어커뮤니케이션 독해/작문 Ⅲ", CourseCategory.필수교양, "염경순", 2);
        createCourse("GEN0714", 2021, "1", "생활과 화학물질", CourseCategory.일반교양, "최재희", 3);
        createCourse("GEN0727", 2021, "1", "생활속의 인공지능", CourseCategory.일반교양, "지준", 3);
        createCourse("CTE0001", 2021, "1", "컴퓨터프로그래밍", CourseCategory.전공필수, "정인환", 3);
        createCourse("CTE0009", 2021, "1", "인터넷윤리와 보안개론", CourseCategory.전공선택, "최원석", 3);

        // 2021 - 2
        createCourse("CCA0005", 2021, "2", "삶과 꿈", CourseCategory.필수교양, "김은경", 2);
        createCourse("GEN0672", 2021, "2", "글로벌 시대의 젠더와 정의", CourseCategory.필수교양, "김은경", 2);
        createCourse("GEN0834", 2021, "2", "다문화 여행과 세계시민성", CourseCategory.필수교양, "박선옥", 2);
        createCourse("REQ0023", 2021, "2", "사고와 표현(발표와 토론)", CourseCategory.필수교양, "이희영", 2);
        createCourse("REQ0024", 2021, "2", "디자인 Thinking", CourseCategory.필수교양, "김선용", 2);
        createCourse("REQ0423", 2021, "2", "영어커뮤니케이션 청취/회화 Ⅲ", CourseCategory.필수교양, "매튜", 2);
        createCourse("GEN0640", 2021, "2", "일반생물", CourseCategory.일반교양, "최재희", 3);
        createCourse("GEN0845", 2021, "2", "공학 커뮤니케이션의 이해", CourseCategory.일반교양, "이희영", 3);
        createCourse("CTE0002", 2021, "2", "웹프로그래밍기초", CourseCategory.전공필수, "이재문", 3);

        // 2022 - 1
        createCourse("GEN0625", 2022, "1", "Basic Career English", CourseCategory.일반교양, "김영아", 3);
        createCourse("GEN0858", 2022, "1", "빅데이터와 인공지능 세상", CourseCategory.일반교양, "김상봉", 3);
        createCourse("V020001", 2022, "1", "객체지향언어1", CourseCategory.전공선택, "황기태", 3);
        createCourse("V020002", 2022, "1", "자료구조", CourseCategory.전공선택, "유상미", 3);
        createCourse("V020003", 2022, "1", "컴퓨터구조", CourseCategory.전공선택, "황호영", 3);
        createCourse("V020004", 2022, "1", "프로그래밍랩", CourseCategory.전공선택, "정인환", 3);
        createCourse("V020005", 2022, "1", "프로그래밍언어론", CourseCategory.전공선택, "김인희", 3);

        // 2022 - 2
        createCourse("U121034", 2022, "2", "상상과 창조", CourseCategory.일반교양, "강사", 3);
        createCourse("V021003", 2022, "2", "모바일&스마트시스템", CourseCategory.전공필수, "이재문", 3);
        createCourse("V024003", 2022, "2", "웹프로그래밍", CourseCategory.전공필수, "심규현", 3);
        createCourse("V020007", 2022, "2", "객체지향언어2", CourseCategory.전공선택, "황기태", 3);
        createCourse("V020008", 2022, "2", "데이터통신", CourseCategory.전공선택, "황호영", 3);
        createCourse("V020010", 2022, "2", "알고리즘", CourseCategory.전공선택, "유상미", 3);
        createCourse("V020011", 2022, "2", "오픈소스소프트웨어", CourseCategory.전공선택, "한기준", 3);

        // 2023 - 1
        createCourse("U121023", 2023, "1", "색채심리와 현대생활", CourseCategory.일반교양, "강사", 3);
        createCourse("U122025", 2023, "1", "이미지관리와 커뮤니케이션", CourseCategory.일반교양, "강사", 3);
        createCourse("V021004", 2023, "1", "안드로이드프로그래밍", CourseCategory.전공필수, "허준영", 3);
        createCourse("V024004", 2023, "1", "웹서버프로그래밍", CourseCategory.전공필수, "김광섭", 3);
        createCourse("V020012", 2023, "1", "데이터베이스", CourseCategory.전공선택, "장재영", 3);
        createCourse("V020013", 2023, "1", "소프트웨어공학", CourseCategory.전공선택, "정인상", 3);
        createCourse("V020014", 2023, "1", "운영체제", CourseCategory.전공선택, "황기태", 3);

        // 2023 - 2
        createCourse("GEN0745", 2023, "2", "지식정보의 자원활용", CourseCategory.일반교양, "박성재", 3);
        createCourse("V021006", 2023, "2", "고급모바일프로그래밍", CourseCategory.전공필수, "허준영", 3);
        createCourse("V024005", 2023, "2", "웹프레임워크1", CourseCategory.전공필수, "박승현", 3);
        createCourse("M111005", 2023, "2", "상상설계 진로1", CourseCategory.전공선택, "강사", 3);
        createCourse("M111006", 2023, "2", "상상설계 진로2", CourseCategory.전공선택, "강사", 3);
        createCourse("V020017", 2023, "2", "네트워크프로그래밍", CourseCategory.전공선택, "신성", 3);
        createCourse("V020018", 2023, "2", "설계패턴", CourseCategory.전공선택, "정인상", 3);
    }

    // 수강신청 정보 생성
    void initEnrollments() {
        createEnrollment("V020031", "클라우드", CourseCategory.전공선택, "김성동", 3);
        createEnrollment("GEN0901", "국가안보론", CourseCategory.일반교양, "류우식", 3);
        createEnrollment("GEN0638", "대학수학 II", CourseCategory.일반교양, "민경진", 3);
    }

    // 수강 정보 생성을 위한 메소드
    private void createCourse(String courseCode, int year, String semester, String courseName, CourseCategory courseCategory, String professor, int credits) {
        Courses course = Courses.builder()
                .courseCode(courseCode)
                .year(year)
                .semester(semester)
                .courseName(courseName)
                .courseCategory(courseCategory)
                .professor(professor)
                .credits(credits)
                .build();
        coursesRepository.save(course);
    }

    // 수강신청 정보 생성을 위한 메소드
    private void createEnrollment(String courseCode, String courseName, CourseCategory courseCategory, String professor, int credits) {
        Enrollments enrollment = Enrollments.builder()
                .courseCode(courseCode)
                .courseName(courseName)
                .courseCategory(courseCategory)
                .professor(professor)
                .credits(credits)
                .year(2024)
                .semester("2")
                .registered(false)
                .build();

        enrollmentsRepository.save(enrollment);
    }
}
