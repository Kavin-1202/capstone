package com.capstone.adminservice.service;


import com.capstone.adminservice.client.UserCredentialDTO;
import com.capstone.adminservice.client.UserManagementFeignClient;
import com.capstone.adminservice.dto.EmployeeDTO;
import com.capstone.adminservice.dto.EmployeeResponse;
import com.capstone.adminservice.entity.CourseAssignment;
import com.capstone.adminservice.entity.Employee;
import com.capstone.adminservice.entity.Roles;
import com.capstone.adminservice.repository.CourseAssignmentRepository;
import com.capstone.adminservice.repository.EmployeeRepository;
import com.capstone.adminservice.utils.EmployeeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    String sender;

    @Autowired
    UserManagementFeignClient userClient;

    @Autowired
    CourseAssignmentRepository courseAssignmentRepository;


//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;  // Autowire the BCryptPasswordEncoder


    // Get all Employees
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .map(employee -> {
                    EmployeeDTO dto = new EmployeeDTO();
                    dto.setUsername(employee.getUsername());
                    dto.setEmail(employee.getEmail());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    // Get Employee by ID
    public EmployeeDTO getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .map(employee -> {
                    EmployeeDTO dto = new EmployeeDTO();
                    dto.setUsername(employee.getUsername());
                    dto.setEmail(employee.getEmail());
                    return dto;
                })
                .orElse(null);
    }


    // Delete Employee by ID
    public void deleteEmployeeById(Long id) {
        employeeRepository.deleteById(id);
    }


    //send bulk emails

    public void addEmployees(List<String> emails){

        String subject = "FYI:Login Credentials";
//
        for(String email:emails){
            SimpleMailMessage simpleMailMessage =  new SimpleMailMessage();
            simpleMailMessage.setFrom(sender);
            simpleMailMessage.setTo(email);
            simpleMailMessage.setSubject(subject);

            String password = EmployeeUtils.generateRandomString(8);
            String body = email + "\n" + password;
            simpleMailMessage.setText(body);
            javaMailSender.send(simpleMailMessage);

            String username = email.substring(0, email.indexOf('@'));
            Employee employee = new Employee();
            employee.setUsername(username);
            employee.setEmail(email);
            employee.setPassword(password);
            employee.setRole(Roles.EMPLOYEE);



           UserCredentialDTO userCredentialDTO = new UserCredentialDTO();
           userCredentialDTO.setUsername(username);
           userCredentialDTO.setPassword(password);
           userCredentialDTO.setEmail(email);
           userCredentialDTO.setAccountid(0L);
           userCredentialDTO.setAccountname(null);
           userCredentialDTO.setRole(Roles.EMPLOYEE);
           userClient.createUser(userCredentialDTO);

            employeeRepository.save(employee);


        }




    }

    public List<EmployeeResponse> getCoursesAssignedToEmployeeByUsername(String username) {
        System.out.println("Fetching courses for username: " + username); // Log username

        List<CourseAssignment> courseAssignments = courseAssignmentRepository.findCourseAssignmentsByEmployeeUsername(username);

        System.out.println("Found assignments: " + courseAssignments.size()); // Log the number of assignments found

        return courseAssignments.stream()
                .map(courseAssignment -> {
                    EmployeeResponse dto = new EmployeeResponse();
                    dto.setCoursename(courseAssignment.getCourse().getCoursename());
                    dto.setDescription(courseAssignment.getCourse().getDescription());
                    dto.setResourcelinks(courseAssignment.getCourse().getResourcelinks());
                    dto.setOtherlinks(courseAssignment.getCourse().getOtherlinks());
                    dto.setOutcomes(courseAssignment.getCourse().getOutcomes());
                    dto.setAssignedDate(courseAssignment.getAssignedDate());
                    dto.setDeadline(courseAssignment.getDeadline());
                    dto.setCoursestatus(courseAssignment.getCoursestatus());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}

