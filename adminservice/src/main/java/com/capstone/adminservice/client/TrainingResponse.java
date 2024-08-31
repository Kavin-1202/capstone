package com.capstone.adminservice.client;

import com.capstone.adminservice.entity.Status;
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
    private String requestorname;
    private String coursename;
    private Status status;
    private LocalDateTime createddate;
}
