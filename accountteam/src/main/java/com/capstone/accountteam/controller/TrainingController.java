package com.capstone.accountteam.controller;

//import com.capstone.accountteam.entity.Mentor;
//import com.capstone.accountteam.entity.Message;
import com.capstone.accountteam.entity.TrainingRequest;
//import com.capstone.accountteam.repository.MentorRepository;
//import com.capstone.accountteam.repository.MessageRepository;
import com.capstone.accountteam.repository.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TrainingController {

    @Autowired
    private TrainingRepository trainingRequestRepository;

//    @Autowired
//    private MentorRepository mentorRepository;
//
//    @Autowired
//    private MessageRepository messageRepository;

    @PostMapping("/trainingRequests")
    public ResponseEntity<TrainingRequest> submitTrainingRequest(@RequestBody TrainingRequest trainingRequest) {
        trainingRequest.setCreateddate(LocalDateTime.now());
        TrainingRequest savedRequest = trainingRequestRepository.save(trainingRequest);
        return ResponseEntity.ok(savedRequest);
    }

    @GetMapping("/trainingRequests")
    public ResponseEntity<List<TrainingRequest>> getAllTrainingRequests() {
        List<TrainingRequest> requests = trainingRequestRepository.findAll();
        return ResponseEntity.ok(requests);
    }

//    @GetMapping("/mentors/{mentorId}/dashboard")
//    public ResponseEntity<Mentor> getMentorDashboard(@PathVariable Long mentorId) {
//        Mentor mentor = mentorRepository.findById(mentorId).orElse(null);
//        if (mentor != null) {
//            return ResponseEntity.ok(mentor);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @PostMapping("/mentors/{mentorId}/employees/{employeeId}/sendMessage")
//    public ResponseEntity<Message> sendMessage(@PathVariable Long mentorId,
//                                               @PathVariable Long employeeId,
//                                               @RequestBody String content) {
//        Message message = new Message();
//        message.setMentorId(mentorId);
//        message.setEmployeeId(employeeId);
//        message.setContent(content);
//        message.setSentDate(LocalDateTime.now());
//        Message savedMessage = messageRepository.save(message);
//        return ResponseEntity.ok(savedMessage);
//    }
}

