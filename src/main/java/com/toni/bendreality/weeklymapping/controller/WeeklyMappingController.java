package com.toni.bendreality.weeklymapping.controller;

import com.toni.bendreality.dailyaction.model.DailyActionDTO;
import com.toni.bendreality.dailyaction.model.DailyActionViewDTO;
import com.toni.bendreality.security.check.RolesAllowed;
import com.toni.bendreality.weeklymapping.model.WeeklyMappingDTO;
import com.toni.bendreality.weeklymapping.model.WeeklyMappingViewDTO;
import com.toni.bendreality.weeklymapping.service.WeeklyMappingService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/weekly_mapping")
@RequiredArgsConstructor
public class WeeklyMappingController {

    private final WeeklyMappingService weeklyMappingService;

    @RolesAllowed(value={"ADMIN","REGULAR"})
    @PostMapping
    public ResponseEntity<WeeklyMappingViewDTO> createWeeklyMapping(@Valid @RequestBody WeeklyMappingDTO weeklyMappingDTO){
        try{
            WeeklyMappingViewDTO added=weeklyMappingService.addWeeklyMapping(weeklyMappingDTO);
            return new ResponseEntity<>(added, HttpStatus.OK);
        }catch (RuntimeException ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RolesAllowed(value={"ADMIN","REGULAR"})
    @PutMapping
    public ResponseEntity<WeeklyMappingViewDTO> updateWeeklyMapping(@RequestBody @Valid WeeklyMappingDTO weeklyMappingDTO){
        try{
            WeeklyMappingViewDTO updated=weeklyMappingService.updateWeeklyMapping(weeklyMappingDTO);
            return new ResponseEntity<>(updated,HttpStatus.OK);
        }catch (EntityNotFoundException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (RuntimeException ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RolesAllowed(value={"ADMIN","REGULAR"})
    @GetMapping
    public ResponseEntity<WeeklyMappingViewDTO> getWeeklyMappingForUser(){
        try{
            WeeklyMappingViewDTO weeklyMapping=weeklyMappingService.getWeeklyMappingForUser();
            return new ResponseEntity<>(weeklyMapping,HttpStatus.OK);
        }catch (EntityNotFoundException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (RuntimeException ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
