package com.example.application.repos;

import com.example.application.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepo extends JpaRepository<Course, Long> {
    Course findByName(String name);
}
