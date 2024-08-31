package com.capstone.adminservice.repository;

import com.capstone.adminservice.entity.CourseAssignment;
import com.capstone.adminservice.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CourseAssignmentRepository extends JpaRepository<CourseAssignment,Long> {


    @Query("SELECT e.username FROM CourseAssignment ca " +
            "JOIN ca.course c " +
            "JOIN ca.employees e " +
            "WHERE c.coursename = ?1")
    List<String> findEmployeesByCourseName(String coursename);
}
