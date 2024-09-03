package com.capstone.adminservice.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

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
    private LocalDate createddate;
    private int requiredemployees;
}
