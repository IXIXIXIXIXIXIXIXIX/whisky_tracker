package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DistilleryController {

    @Autowired
    DistilleryRepository distilleryRepository;

    @GetMapping(value = "/distilleries")
    public ResponseEntity<List<Distillery>> getAllDistilleries(
            @RequestParam(name = "region", required = false) String region,
            @RequestParam(name = "age", required = false) String age
    ) {

        if (region != null) {
            return new ResponseEntity<List<Distillery>>(distilleryRepository.findDistilleryByRegion(region), HttpStatus.OK);
        }

        if (age != null) {
            return new ResponseEntity<List<Distillery>>(distilleryRepository.findDistilleriesByWhiskiesAge(Integer.parseInt(age)), HttpStatus.OK);
        }
        return new ResponseEntity<>(distilleryRepository.findAll(), HttpStatus.OK);

    }

}


