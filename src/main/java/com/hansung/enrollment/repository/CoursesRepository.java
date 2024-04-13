package com.hansung.enrollment.repository;

import com.hansung.enrollment.entity.Courses;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursesRepository extends JpaRepository<Courses, String> {
    @Query("SELECT c.year, c.semester, SUM(c.credits) FROM Courses c GROUP BY c.year, c.semester ORDER BY c.year DESC, c.semester DESC")
    List<Object[]> findCreditsPerSemester();

    List<Courses> findByYearAndSemester(int year, String semester);
}
