package com.toni.bendreality.dailyaction.controller;

import com.toni.bendreality.dailyaction.model.DailyAction;
import com.toni.bendreality.dailyaction.model.DailyActionDTO;
import com.toni.bendreality.dailyaction.model.DailyActionViewDTO;
import com.toni.bendreality.dailyaction.service.DailyActionService;
import com.toni.bendreality.security.check.RolesAllowed;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.toni.bendreality.security.ApplicationConfiguration.getCurrentUserId;

@RestController
@RequestMapping("/daily_action")
@RequiredArgsConstructor
public class DailyActionController {

    private final DailyActionService dailyActionService;

    @RolesAllowed(value={"ADMIN","REGULAR"})
    @PostMapping
    public ResponseEntity<DailyActionViewDTO> createDailyAction(@Valid @RequestBody DailyActionDTO dailyActionDTO){
        try{
            DailyActionViewDTO added=dailyActionService.addDailyAction(dailyActionDTO);
            return new ResponseEntity<>(added, HttpStatus.OK);
        }catch (RuntimeException ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RolesAllowed(value={"ADMIN","REGULAR"})
    @PutMapping
    public ResponseEntity<DailyActionViewDTO> updateDailyAction(@RequestBody @Valid DailyActionDTO dailyActionDTO){
        try{
            DailyActionViewDTO updated=dailyActionService.updateDailyAction(dailyActionDTO);
            return new ResponseEntity<>(updated,HttpStatus.OK);
        }catch (EntityNotFoundException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (RuntimeException ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RolesAllowed(value={"ADMIN","REGULAR"})
    @GetMapping
    public ResponseEntity<DailyActionViewDTO> getDailyActionForUser(){
        try{
            DailyActionViewDTO dailyAction=dailyActionService.getDailyActionForUser();
            return new ResponseEntity<>(dailyAction,HttpStatus.OK);
        }catch (EntityNotFoundException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (RuntimeException ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
