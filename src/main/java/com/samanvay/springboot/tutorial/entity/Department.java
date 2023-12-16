package com.samanvay.springboot.tutorial.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Department {

    @Id
    @GeneratedValue ( strategy = GenerationType.AUTO)
    private Long departmentId;

    @NotBlank(message = "Please add valid Department(s) name")
    private String departmentName;
    private String departmentAddress;
    private String departmentCode;
}
