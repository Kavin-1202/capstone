package com.capstone.accountteam.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainingRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String atid;
    private String coursename;
    private String description;
    private String concepts;
    private String duration;
    private String position;
    private String status; // PENDING or COMPLETED
    private LocalDateTime createddate;


//    @ManyToOne
//    @JoinColumn(name = "employee_id", nullable = false)
//    private Employee employee;

//    @ManyToOne
//    @JoinColumn(name = "course_id", nullable = false)
//    private Course course;
}
