package com.capstone.adminservice.client;

import com.capstone.adminservice.dto.Status;
import com.capstone.adminservice.dto.TrainingRequest;
import com.capstone.adminservice.dto.TrainingResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "Accountteam",url = "http://localhost:9000/api")
public interface Accountclient {
    @GetMapping("/trainingRequest/{requestid}")
    public ResponseEntity<TrainingRequest> getTrainingRequestsByRequestid(@PathVariable Long requestid);

    @GetMapping("/trainingRequests")
    public ResponseEntity<List<TrainingResponse>> getAllTrainingRequests();
    @PutMapping("/trainingRequest/{requestid}/status")
    ResponseEntity<Void> updateTrainingRequestStatus(@PathVariable Long requestid, @RequestParam Status status);
}
