package com.toni.bendreality.mastervision.service;

import com.toni.bendreality.mastervision.mapper.MasterVisionMapper;
import com.toni.bendreality.mastervision.model.MasterVision;
import com.toni.bendreality.mastervision.model.MasterVisionDTO;
import com.toni.bendreality.mastervision.model.MasterVisionViewDTO;
import com.toni.bendreality.mastervision.repository.MasterVisionRepository;
import com.toni.bendreality.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.toni.bendreality.security.ApplicationConfiguration.getCurrentUserId;

@Slf4j
@AllArgsConstructor
@Service
@Transactional(readOnly = true)
public class MasterVisionService {

    private final MasterVisionRepository masterVisionRepository;
    private final MasterVisionMapper masterVisionMapper;
    private final UserRepository userRepository;


    @Transactional
    public MasterVisionViewDTO addMasterVision(MasterVisionDTO masterVisionDTO){
        MasterVision toSave=masterVisionMapper.toEntity(masterVisionDTO);
        toSave.setUser(userRepository.getReferenceById(getCurrentUserId()));
        return masterVisionMapper.toViewDTO(masterVisionRepository.save(toSave));
    }

    public MasterVisionViewDTO getMasterVisionForUser(){
        Optional<MasterVision> toFind=masterVisionRepository.getMasterVisionByUserId(getCurrentUserId());
        if(toFind.isPresent()){
            MasterVision found=toFind.get();
            return masterVisionMapper.toViewDTO(found);
        }else{
            throw new EntityNotFoundException("Couldn't find the master vision");
        }
    }

    @Transactional
    public MasterVisionViewDTO updateMasterVision(MasterVisionDTO masterVisionDTO){
        Optional<MasterVision> toFind=masterVisionRepository.getMasterVisionByUserId(getCurrentUserId());
        if(toFind.isPresent()){
            MasterVision found=toFind.get();
            found.setMasterVision(masterVisionDTO.masterVision());
            found.setIdentityDesign(masterVisionDTO.identityDesign());
            found.setBucketList(masterVisionDTO.bucketList());
            found.setLifestyleDesign(masterVisionDTO.lifestyleDesign());
            found.setMaterialDesires(masterVisionDTO.materialDesires());
            found.setNorthStarGoalOne(masterVisionDTO.northStarGoalOne());
            found.setNorthStarGoalTwo(masterVisionDTO.northStarGoalTwo());
            return masterVisionMapper.toViewDTO(masterVisionRepository.save(found));
        }else{
            throw new EntityNotFoundException("Couldn't find the master vision");
        }
    }


}
