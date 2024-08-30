package com.capstone.accountteam.dtos;
import com.capstone.accountteam.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainingRequestdto {
    private String accountid;
    private String requestorname;
    private String coursename;
    private String description;
    private String concepts;
    private String duration;
    private String employeeposition;
    private int Requiredemployees;
}
