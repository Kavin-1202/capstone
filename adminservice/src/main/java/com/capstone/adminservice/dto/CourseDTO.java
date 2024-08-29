package com.capstone.adminservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {

    @NotBlank(message = "Course name is required")
    @Size(max = 100, message = "Course name should not exceed 100 characters")
    private String coursename;

    @NotBlank(message = "Description is required")
    @Size(max = 500, message = "Description should not exceed 500 characters")
    private String description;

    @NotBlank(message = "Resource links are required")
    private String resourcelinks;

    @Size(max = 300, message = "Additional links should not exceed 300 characters")
    private String otherlinks;

    @NotBlank(message = "Outcomes are required")
    private String outcomes;
}
