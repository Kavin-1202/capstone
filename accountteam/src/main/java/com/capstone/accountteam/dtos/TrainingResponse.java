package com.capstone.accountteam.dtos;

import com.capstone.accountteam.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainingResponse {
    private Long requestid;
    private String accountid;
    private String requestorname;
    private String coursename;
    private Status status; // PENDING or COMPLETED
    private LocalDate createddate;

}
