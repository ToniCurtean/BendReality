package com.toni.bendreality.meditations.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/meditations")
@CrossOrigin
public class MeditationController {

    @GetMapping
    public ResponseEntity<List<String>> getMeditations(){
        return new ResponseEntity<>(Arrays.asList("stillness","second"),HttpStatus.OK);
    }

}
