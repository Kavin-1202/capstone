package com.capstone.adminservice.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class CourseAssignmentDTO {
    private Long courseId;
    private List<String> employeeUsernames; // Change from employeeIds to employeeUsernames
    private LocalDate deadline;

}
