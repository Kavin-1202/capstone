package com.capstone.adminservice.service;

import com.capstone.adminservice.dto.CourseDTO;
import com.capstone.adminservice.entity.Course;
import com.capstone.adminservice.exceptions.ResourceNotFoundException;
import com.capstone.adminservice.repository.CourseRepository;
import com.capstone.adminservice.utils.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
   private final String courseNotFound = "Course not found with id: ";

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public CourseDTO createCourse(CourseDTO courseDTO) {
        Course course = new Course();
        BeanUtils.copyProperties(courseDTO, course);
        course = courseRepository.save(course);
        return CourseMapper.toDTO(course);
    }

    public Optional<CourseDTO> getCourseById(Long id) throws ResourceNotFoundException {
        if (courseRepository.existsById(id))
            return courseRepository.findById(id).map(CourseMapper::toDTO);
        else
            throw new ResourceNotFoundException(courseNotFound + id);

    }

    public Optional<Course> getCoursesByName(String name) throws ResourceNotFoundException {
        Optional<Course> course = courseRepository.findByCoursename(name);
        if(course.isEmpty())
            throw new ResourceNotFoundException(courseNotFound + name);
        return  course;
    }

    public List<CourseDTO> getAllCourses() {
        return courseRepository.findAll().stream()
                .map(CourseMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CourseDTO updateCourse(Long id, CourseDTO courseDTO) throws ResourceNotFoundException {
        if (courseRepository.existsById(id)) {
            Course course = new Course();
            course = CourseMapper.toEntity(courseDTO);
            course.setCourseid(id);
            course = courseRepository.save(course);
            return CourseMapper.toDTO(course);
        } else {
            throw new ResourceNotFoundException(courseNotFound + id);
        }
    }

    public void deleteCourse(Long id) throws ResourceNotFoundException {
        if (courseRepository.existsById(id)) {
            courseRepository.deleteById(id);
        } else {

            throw new ResourceNotFoundException(courseNotFound + id);
        }
    }

}






