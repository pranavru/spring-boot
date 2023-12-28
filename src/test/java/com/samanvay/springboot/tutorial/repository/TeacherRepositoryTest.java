package com.samanvay.springboot.tutorial.repository;

import com.samanvay.springboot.tutorial.entity.Course;
import com.samanvay.springboot.tutorial.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeacherRepositoryTest {
    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    public void SaveTeacher () {
        Course courseDSA = Course.builder()
                .title("DSA")
                .credit(3).build();
        Course courseDBMS = Course.builder()
                .title("DBMS")
                .credit(3).build();

        Teacher teacher = Teacher.builder()
                .firstName("Kajol")
                .lastName("Gupta")
//                .courses(List.of(courseDBMS, courseDSA))
                .build();

        teacherRepository.save(teacher);
    }
}