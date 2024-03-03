package com.toni.bendreality.mastervision.repository;

import com.toni.bendreality.mastervision.model.MasterVision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MasterVisionRepository extends JpaRepository<MasterVision,Integer> {

    Optional<MasterVision> getMasterVisionByUserId(Integer userId);
}
