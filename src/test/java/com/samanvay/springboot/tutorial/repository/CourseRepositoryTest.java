package com.samanvay.springboot.tutorial.repository;

import com.samanvay.springboot.tutorial.entity.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseRepositoryTest {
    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void SaveCourseWithTeacher () {
        Teacher teacher = Teacher.builder()
                .firstName("Jay")
                .lastName("Darji")
                .build();

        Course course = Course.builder()
                .title("ML")
                .credit(6)
                .teacher(teacher)
//                .courseMaterial(courseMaterial)
                .build();

        courseRepository.save(course);
    }

    @Test
    public void printCourses () {
        List<Course> courses = courseRepository.findAll();

        System.out.println(courses);
    }

    @Test
    public void saveCourseWithStudentAndItsTeachers () {
        Teacher teacher = Teacher.builder()
                .firstName("Jay")
                .lastName("Darji")
                .build();

        Guardian guardian = Guardian.builder()
                .name("Kaushil Pranav")
                .emailId("kaushill11@yahoo.com")
                .mobileNumber("2345678901").build();

        Student student = Student.builder()
                .firstName("Pranav")
                .lastName("Ruparelia")
                .emailId("plr24@njit.edu")
                .guardian(guardian)
                .build();
        Course course = Course.builder()
                .title("ML")
                .credit(6)
                .teacher(teacher)
                .students(List.of(student))
                .build();

        courseRepository.save(course);
    }

}