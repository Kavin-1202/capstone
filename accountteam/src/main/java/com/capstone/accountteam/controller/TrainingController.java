package com.capstone.accountteam.controller;
import com.capstone.accountteam.dtos.Dashboarddto;
import com.capstone.accountteam.dtos.TrainingRequestdto;
import com.capstone.accountteam.dtos.TrainingResponse;
import com.capstone.accountteam.entity.Status;
import com.capstone.accountteam.entity.TrainingRequest;
import com.capstone.accountteam.exception.ResourseNotFoundException;
import com.capstone.accountteam.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/accounts")
@CrossOrigin(origins = "http://localhost:3000")
public class TrainingController {

    @Autowired
    private TrainingService trainingService;

    @PostMapping("/sendRequest")
    public ResponseEntity<String> submitTrainingRequest(@RequestBody TrainingRequestdto trainingRequest) {
        String savedRequest=trainingService.requestform(trainingRequest);
        return ResponseEntity.ok(savedRequest);
    }

    @GetMapping("/viewRequests")
    public ResponseEntity<List<TrainingResponse>> getAllTrainingRequests() {
        List<TrainingResponse> requests=trainingService.getAllRequests();
        return ResponseEntity.ok(requests);
    }
    @GetMapping("/viewRequest/{requestid}")
    public ResponseEntity<TrainingRequest> getTrainingRequestsByRequestid(@PathVariable Long requestid) {
        TrainingRequest request = trainingService.getRequestByrequestid(requestid);
        return ResponseEntity.ok(request);
    }
    @ExceptionHandler(ResourseNotFoundException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> handleResourseNotFoundException(ResourseNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.OK).body(ex.getMessage());
    }
    @PutMapping("/trainingRequest/{requestid}/status")
    public ResponseEntity<String> updateTrainingRequestStatus(@PathVariable Long requestid, @RequestParam Status status) {
        trainingService.updateRequestStatus(requestid, status);
        return ResponseEntity.ok("staus changed");
    }

    @GetMapping("/Dashboard/{name}")
    public ResponseEntity<List<Dashboarddto>> getTrainingRequestByRequestName(@PathVariable String name) {
        List<Dashboarddto> request = trainingService.getRequestByrequestname(name);
        return ResponseEntity.ok(request);
    }



}

