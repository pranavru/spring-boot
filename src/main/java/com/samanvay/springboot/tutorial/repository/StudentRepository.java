package com.samanvay.springboot.tutorial.repository;

import com.samanvay.springboot.tutorial.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query ("select s from Student s where s.emailId = ?1")
    Student getStudentByEmailAddress (String emailId);

    @Query ("select s.firstName from Student s where s.emailId = ?1 ")
    String getStudentFirstNameByEmailAddress (String emailId);

    @Query (
            nativeQuery = true,
            value = "SELECT first_name FROM tbl_student s WHERE s.email_address = ?1"
    )
    String getStudentFirstNameByEmailAddressNative (String emailId);

    @Query (
            nativeQuery = true,
            value = "SELECT first_name FROM tbl_student s WHERE s.email_address = :emailId"
    )
    String getStudentFirstNameByEmailAddressNativeNamedParam (
            @Param("emailId") String emailId
    );
}
