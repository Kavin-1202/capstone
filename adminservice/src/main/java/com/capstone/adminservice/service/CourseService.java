package com.capstone.adminservice.service;

import com.capstone.adminservice.client.Accountclient;
import com.capstone.adminservice.dto.*;
import com.capstone.adminservice.entity.Course;
import com.capstone.adminservice.exceptions.ResourceNotFoundException;
import com.capstone.adminservice.repository.CourseRepository;
import com.capstone.adminservice.utils.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final String courseNotFound = "Course not found with id: ";
    private final Accountclient accountclient;
    @Autowired
    public CourseService(CourseRepository courseRepository,Accountclient accountclient ) {
        this.courseRepository = courseRepository;
        this.accountclient=accountclient;
    }
    public Course createCourse(Long requestid,CourseDTO courseDTO) throws ResourceNotFoundException {
        // Fetch the training request details using Feign client
        TrainingRequest request = accountclient.getTrainingRequestsByRequestid(requestid).getBody();

        // Check if the request exists
        if (request == null) {
            throw new ResourceNotFoundException("Training Request not found for id: " + requestid);
        }

        // Check the status of the TrainingRequest
        if (request.getStatus() == Status.Completed) {
            throw new ResourceNotFoundException("Course already created for this training request.");
        }
        // Proceed with course creation since the status is PENDING
        Course course = new Course();
        BeanUtils.copyProperties(courseDTO, course);
        course.setCoursename(request.getCoursename());
        course.setDescription(request.getDescription());
        course.setRequestid(request.getRequestid());
        // Save the course
        Course savedCourse = courseRepository.save(course);
        // Update status of the TrainingRequest to COMPLETED
        accountclient.updateTrainingRequestStatus(requestid, Status.Completed);
        return savedCourse;
    }

    public Optional<FullResponse> getCourseById(Long id) throws ResourceNotFoundException {
        if (courseRepository.existsById(id))
            return courseRepository.findById(id).map(CourseMapper::toDTO);
        else
            throw new ResourceNotFoundException(courseNotFound + id);

    }

    public FullResponse getCoursesByName(String name) throws ResourceNotFoundException {
        Optional<Course> course = courseRepository.findByCoursename(name);
        if(course.isEmpty())
            throw new ResourceNotFoundException(courseNotFound + name);
        FullResponse response=new FullResponse();
        BeanUtils.copyProperties(course,response);
        return  response;
    }

    public List<FullResponse> getAllCourses() {
        List<FullResponse> responses=new ArrayList<>();
        List<Course> courses=courseRepository.findAll();
        for(Course course:courses){
            FullResponse response=new FullResponse();
            BeanUtils.copyProperties(course,response);
            responses.add(response);
        }
        return responses;
    }

    public Course updateCourse(Long id, CourseDTO courseDTO) throws ResourceNotFoundException {
        Course course = courseRepository.findById(id).orElse(null);
        if(course==null){
            throw new ResourceNotFoundException("course not found");
        }
        course.setOtherlinks(courseDTO.getOtherlinks());
        course.setResourcelinks(courseDTO.getResourcelinks());
        course.setOutcomes(courseDTO.getOutcomes());
        course = courseRepository.save(course);
        return course;
    }

    public void deleteCourse(Long id) throws ResourceNotFoundException {
        if (courseRepository.existsById(id)) {
            courseRepository.deleteById(id);
        } else {

            throw new ResourceNotFoundException(courseNotFound + id);
        }
    }
    public List<TrainingResponse> getRequests(){
        return accountclient.getAllTrainingRequests().getBody();
    }
    public TrainingRequest getRequest(Long requestid){
        return accountclient.getTrainingRequestsByRequestid(requestid).getBody();
    }


}






