package com.capstone.adminservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FullResponse {
    long courseid;
    String coursename;
    String description;
    String resourcelinks;
    String otherlinks;
    String outcomes;
}
