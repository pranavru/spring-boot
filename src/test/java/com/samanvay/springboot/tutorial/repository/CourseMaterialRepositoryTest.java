package com.samanvay.springboot.tutorial.repository;

import com.samanvay.springboot.tutorial.entity.Course;
import com.samanvay.springboot.tutorial.entity.CourseMaterial;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseMaterialRepositoryTest {

    @Autowired
    private CourseMaterialRepository courseMaterialRepository;

    @Test
    public void SaveCourseMaterial () {
        Course course = Course.builder()
                .title("Online JAVA Class")
                .credit(3)
                .build();


        CourseMaterial courseMaterial = CourseMaterial.builder()
                .url("https://samanvay.org")
                .course(course)
                .build();

        courseMaterialRepository.save(courseMaterial);
    }

    @Test
    public void printAllCourseMaterials () {
        List<CourseMaterial> courses = courseMaterialRepository.findAll();

        System.out.println(courses);
    }
}