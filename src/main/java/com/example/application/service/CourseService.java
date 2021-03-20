package com.example.application.service;

import com.example.application.domain.Course;
import com.example.application.domain.Lesson;
import com.example.application.domain.Team;
import com.example.application.repos.CourseRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {
    private final CourseRepo courseRepo;

    public CourseService(CourseRepo courseRepo) {
        this.courseRepo = courseRepo;
    }
    public List<Course> getAllCourses(){
        return courseRepo.findAll();
    }
    public void delete(Course course){
        courseRepo.delete(course);
    }

    public String getStringName(Team team){
        if (team.getCourse() != null) return team.getCourse().getName()
                ;
        return "not set";
    }
    public String getCourseNameByLesson(Lesson lesson){
        if(lesson.getCourse()==null) return "not set"
                ;
        else return lesson.getCourse().getName()
                ;
    }
    public Course getCourseByName(String name){
        return courseRepo.findByName(name);
    }

    public List<String> getAllCoursesNames(){
        return courseRepo.findAll().stream().map(Course::getName).collect(Collectors.toList());
    }
    public void save(Course course){
        courseRepo.save(course);
    }

}
