package com.capstone.adminservice.service;
import com.capstone.adminservice.dto.CourseAssignmentDTO;
import com.capstone.adminservice.entity.Course;
import com.capstone.adminservice.entity.CourseAssignment;
import com.capstone.adminservice.entity.Employee;
import com.capstone.adminservice.exceptions.ResourceNotFoundException;
import com.capstone.adminservice.repository.CourseAssignmentRepository;
import com.capstone.adminservice.repository.CourseRepository;
import com.capstone.adminservice.repository.EmployeeRepository;
import com.capstone.adminservice.utils.CourseAssignmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CourseAssignmentService {

    @Autowired
    private CourseAssignmentRepository courseAssignmentRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    public CourseAssignment assignCourseToEmployees(CourseAssignmentDTO courseAssignmentDTO) throws ResourceNotFoundException {

        Long courseId = courseAssignmentDTO.getCourseId();
        List<String> employeeUsernames = courseAssignmentDTO.getEmployeeUsernames();
        LocalDate deadline = courseAssignmentDTO.getDeadline();

        Course course = courseRepository.findById(courseAssignmentDTO.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        List<Employee> employees = employeeRepository.findByUsernameIn(employeeUsernames);

        CourseAssignment courseAssignment = CourseAssignmentMapper.
                getCourseAssignment(course, employees,deadline);

        return courseAssignmentRepository.save(courseAssignment);
    }


    public List<String> getAssignments(String name) throws ResourceNotFoundException {

        Course course = courseRepository.findByCoursenameIgnoreCase(name)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

       return courseAssignmentRepository.findEmployeesByCourseName(name);
    }
}
