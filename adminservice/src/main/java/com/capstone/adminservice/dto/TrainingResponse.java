package com.capstone.adminservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainingResponse {
    private Long requestid;
    private String accountid;
    private String coursename;
    private Status status;
    private LocalDateTime createddate;
}
