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
    private Long courseid;
    private Long requestid;
    @Column(columnDefinition = "VARCHAR(255)",unique = true)
    private String coursename;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(columnDefinition = "TEXT")
    private String resourcelinks;
    @Column(columnDefinition = "TEXT")
    private String otherlinks;
    @Column(columnDefinition = "TEXT")
    private String outcomes;

}
