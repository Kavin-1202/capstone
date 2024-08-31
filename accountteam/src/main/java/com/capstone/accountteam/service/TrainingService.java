package com.capstone.accountteam.service;

import com.capstone.accountteam.dtos.Dashboarddto;
import com.capstone.accountteam.dtos.TrainingRequestdto;
import com.capstone.accountteam.dtos.TrainingResponse;
import com.capstone.accountteam.entity.Status;
import com.capstone.accountteam.entity.TrainingRequest;
import com.capstone.accountteam.exception.ResourseNotFoundException;
import com.capstone.accountteam.repository.TrainingRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TrainingService {
    @Autowired
    private TrainingRepository trainingRequestRepository;
    public String requestform(TrainingRequestdto trainingRequest) {
        TrainingRequest request=new TrainingRequest();
        trainingRequestRepository.save(request);
        BeanUtils.copyProperties(trainingRequest, request);
        request.setCreateddate(LocalDate.now());
        request.setStatus(Status.Pending);
        trainingRequestRepository.save(request);
        return "Request created successfully";
    }

    public List<TrainingResponse> getAllRequests() {
        List<TrainingRequest> requests=trainingRequestRepository.findAll();
        List<TrainingResponse> sendrequest = new ArrayList<>();
        for(TrainingRequest trainingRequest:requests){
            TrainingResponse request=new TrainingResponse();
            BeanUtils.copyProperties(trainingRequest, request);
            sendrequest.add(request);
        }
        return sendrequest;
    }

    public TrainingRequest getRequestByrequestid(Long requestid) {
        TrainingRequest request=trainingRequestRepository.findById(requestid).orElse(null);
        if(request==null){
            throw new ResourseNotFoundException("Request not found");
        }
        return request;
    }

    public void updateRequestStatus(Long requestid, Status status) {
        TrainingRequest request = trainingRequestRepository.findById(requestid)
                .orElseThrow(() -> new ResourseNotFoundException("Training Request not found for id: " + requestid));
        request.setStatus(status);
        trainingRequestRepository.save(request);
    }
    public List<Dashboarddto> getRequestByrequestname(String requestorname) {
        List<TrainingRequest> requests=trainingRequestRepository.findByRequestorname(requestorname);
        List<Dashboarddto> dashboarddtoList=new ArrayList<>();
        for(TrainingRequest request:requests ){
            Dashboarddto dto=new Dashboarddto();
            BeanUtils.copyProperties(request, dto);
            dashboarddtoList.add(dto);
        }
        if(dashboarddtoList.isEmpty()){
            throw new ResourseNotFoundException("No requests found");
        }
        return dashboarddtoList;
    }
}
