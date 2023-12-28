package com.samanvay.springboot.tutorial.repository;

import com.samanvay.springboot.tutorial.entity.Guardian;
import com.samanvay.springboot.tutorial.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void saveStudent () {
        Student student = Student.builder()
                .emailId("plr22@njit.edu")
                .firstName("Pranav")
                .lastName("Ruparelia")
//                .guardianName("Prabodh swami")
//                .guardianEmailId("hariprabodham@yds.org")
//                .guardianMobileNumber("1222122221")
                .build();

        studentRepository.save(student);
    }

    @Test
    public void saveStudentWithGuardian () {
        Guardian guardian = Guardian.builder()
                .name("Prabodh swami")
                .emailId("hariprabodham@yds.org")
                .mobileNumber("1222122221")
                .build();
        Student student = Student.builder()
                .emailId("plr23@njit.edu")
                .firstName("Pranav")
                .lastName("Ruparelia")
                .guardian(guardian)
                .build();

        studentRepository.save(student);
    }

    @Test
    public void getAllStudents () {
        List<Student> students = studentRepository.findAll();

        System.out.println(students);
    }

    @Test
    public void getStudentByEmailAddress () {
        Student student = studentRepository.getStudentByEmailAddress("plr23@njit.edu");

        System.out.println(student);
    }

    @Test
    public void getStudentByEmailAddressNative () {
        String student = studentRepository.getStudentFirstNameByEmailAddressNative("plr23@njit.edu");

        System.out.println(student);
    }

    @Test
    public void getStudentByEmailAddressNativeNamedParam () {
        String student = studentRepository.getStudentFirstNameByEmailAddressNativeNamedParam("plr23@njit.edu");

        System.out.println(student);
    }

    @Test
    public void getStudentFirstNameByEmailAddress () {
        String student = studentRepository.getStudentFirstNameByEmailAddress("plr23@njit.edu");

        System.out.println(student);
    }
}