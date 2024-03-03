package com.toni.bendreality.mastervision.controller;

import com.toni.bendreality.mastervision.model.MasterVisionDTO;
import com.toni.bendreality.mastervision.model.MasterVisionViewDTO;
import com.toni.bendreality.mastervision.repository.MasterVisionRepository;
import com.toni.bendreality.mastervision.service.MasterVisionService;
import com.toni.bendreality.security.check.RolesAllowed;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/master_vision")
public class MasterVisionController {

    private final MasterVisionService masterVisionService;

    @RolesAllowed(value={"ADMIN","REGULAR"})
    @PostMapping
    public ResponseEntity<MasterVisionViewDTO> createMasterVision(@Valid @RequestBody MasterVisionDTO masterVisionDTO){
        try{
            MasterVisionViewDTO added=masterVisionService.addMasterVision(masterVisionDTO);
            return new ResponseEntity<>(added, HttpStatus.OK);
        }catch (RuntimeException ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RolesAllowed(value={"ADMIN","REGULAR"})
    @PutMapping
    public ResponseEntity<MasterVisionViewDTO> updateMasterVision(@RequestBody @Valid MasterVisionDTO masterVisionDTO){
        try{
            MasterVisionViewDTO updated=masterVisionService.updateMasterVision(masterVisionDTO);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        }catch (EntityNotFoundException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (RuntimeException ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RolesAllowed(value={"ADMIN","REGULAR"})
    @GetMapping
    public ResponseEntity<MasterVisionViewDTO> getMasterVisionForUser(){
        try{
            MasterVisionViewDTO masterVision=masterVisionService.getMasterVisionForUser();
            return new ResponseEntity<>(masterVision,HttpStatus.OK);
        }catch (EntityNotFoundException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (RuntimeException ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}