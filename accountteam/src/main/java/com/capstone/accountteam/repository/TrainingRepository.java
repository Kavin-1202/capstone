package com.capstone.accountteam.repository;

import com.capstone.accountteam.entity.TrainingRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingRepository extends JpaRepository<TrainingRequest, Long> {

}
