package com.capstone.adminservice.client;

import com.capstone.adminservice.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainingRequestDTO {
    private Long requestid;
    private String accountid;
    private String requestorname;
    private String coursename;
    private String description;
    private String concepts;
    private String duration;
    private String employeeposition;
    private Status status; // PENDING or COMPLETED
    private LocalDateTime createddate;
    private int Requiredemployees;
}
