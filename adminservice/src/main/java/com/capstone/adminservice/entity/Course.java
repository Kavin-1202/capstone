package com.capstone.adminservice.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long courseid;

    long requestid; //foreignkey


    @Column(columnDefinition = "VARCHAR(255)",unique = true)
    String coursename;

    @Column(columnDefinition = "TEXT")
    String description;

    @Column(columnDefinition = "TEXT")
    String resourcelinks;

    @Column(columnDefinition = "TEXT")
    String otherlinks;

    @Column(columnDefinition = "TEXT")
    String outcomes;

}
