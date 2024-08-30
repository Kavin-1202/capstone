package com.capstone.adminservice.service;


import com.capstone.adminservice.dto.EmployeeDTO;
import com.capstone.adminservice.entity.Employee;
import com.capstone.adminservice.repository.EmployeeRepository;
import com.capstone.adminservice.utils.EmployeeUtils;
import org.springframework.beans.factory.annotation.Autowired;
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


//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;  // Autowire the BCryptPasswordEncoder

    // Create a new Employee with encoded password
    public Employee createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setUsername(employee.getUsername());
        employee.setPassword(employeeDTO.getPassword());  // Encode the password
        return employeeRepository.save(employee);
    }

    // Get all Employees
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .map(employee -> {
                    EmployeeDTO dto = new EmployeeDTO();
                    dto.setUsername(employee.getUsername());
                    dto.setPassword(employee.getPassword());
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
                    dto.setPassword(employee.getPassword());
                    return dto;
                })
                .orElse(null);
    }


    // Delete Employee by ID
    public void deleteEmployeeById(Long id) {
        employeeRepository.deleteById(id);
    }


    //send bulk emails

    public void sendMail(List<String> emails){

        String subject = "FYI:Login Credentials";
        String sender = "sakthisiddu01@gmail.com";

        for(String email:emails){
            SimpleMailMessage simpleMailMessage =  new SimpleMailMessage();
            simpleMailMessage.setFrom(sender);
            simpleMailMessage.setTo(email);
            simpleMailMessage.setSubject(subject);

            String password = EmployeeUtils.generateRandomString(8);
            String body = email + "\n" + password;
            simpleMailMessage.setText(body);

            Employee employee = new Employee();
            employee.setUsername(email);
            employee.setPassword(password);
            employeeRepository.save(employee);
            javaMailSender.send(simpleMailMessage);
        }




    }
}

