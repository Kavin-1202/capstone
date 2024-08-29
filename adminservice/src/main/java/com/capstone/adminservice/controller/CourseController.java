package com.capstone.adminservice.controller;

import com.capstone.adminservice.dto.CourseDTO;
import com.capstone.adminservice.entity.Course;
import com.capstone.adminservice.exceptions.ResourceNotFoundException;
import com.capstone.adminservice.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/courses")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<CourseDTO> createCourse(@Valid @RequestBody CourseDTO courseDTO) {
        CourseDTO createdCourse = courseService.createCourse(courseDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCourse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable Long id) {
        Optional<CourseDTO> courseDTO = courseService.getCourseById(id);
        return courseDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }


    @GetMapping
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        List<CourseDTO> courseDTOs = courseService.getAllCourses();
        return ResponseEntity.ok(courseDTOs);
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<Course> getCoursesByName(@PathVariable String name) {
        Optional<Course> course = courseService.getCoursesByName(name);
        if (course.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(course.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> updateCourse(@PathVariable Long id, @Valid @RequestBody CourseDTO courseDTO) throws ResourceNotFoundException {
        CourseDTO updatedCourse = courseService.updateCourse(id, courseDTO);
        return ResponseEntity.ok(updatedCourse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) throws ResourceNotFoundException {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
}
