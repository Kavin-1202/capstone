package com.capstone.accountteam.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainingRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long requestid;
    private String accountid;
    @Column(columnDefinition = "VARCHAR(255)")
    private String requestorname;
    @Column(columnDefinition = "VARCHAR(255)",unique = true)
    private String coursename;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(columnDefinition = "TEXT")
    private String concepts;
    @Column(columnDefinition = "VARCHAR(255)")
    private String duration;
    @Column(columnDefinition = "VARCHAR(255)")
    private String employeeposition;
    private Status status; // PENDING or COMPLETED
    @Column(columnDefinition = "DATE")
    private LocalDate createddate;
    private int requiredemployees;

}
