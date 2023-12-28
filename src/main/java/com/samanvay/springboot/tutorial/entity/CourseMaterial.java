package com.samanvay.springboot.tutorial.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString (
        exclude = "course"
)
public class CourseMaterial {
    @Id
    @SequenceGenerator(
            name = "course_material_sequence",
            sequenceName = "course_material_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_material_sequence"
    )
    private Long courseMaterialId;

    @Column (
            name = "url",
            nullable = false
    )
    private String url;

    @OneToOne (
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, // Allows us to remove column's from data retrieval
            optional = false // This means the user has to mandatory add course material with its course
    )
    @JoinColumn (
            name = "course_id",
            referencedColumnName = "courseId"
    )
    private Course course;
}
