package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WhiskyController {

    @Autowired
    WhiskyRepository whiskyRepository;

    @GetMapping(value = "/whiskies")
    public ResponseEntity<List<Whisky>> getAllWhiskies(

            @RequestParam(name = "year", required = false) String year,
            @RequestParam(name = "age", required = false) String age,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "region", required = false) String region

    ) {
        if (year != null) {
            return new ResponseEntity<List<Whisky>>(whiskyRepository.findWhiskyByYear(Integer.parseInt(year)), HttpStatus.OK);
        }

        if (age != null && name != null) {
            return new ResponseEntity<List<Whisky>>(whiskyRepository.findWhiskyByAgeAndDistilleryName(Integer.parseInt(age), name), HttpStatus.OK);
        }

        if (region != null) {
            return new ResponseEntity<List<Whisky>>(whiskyRepository.findWhiskyByDistilleryRegion(region), HttpStatus.OK);
        }
        return new ResponseEntity<>(whiskyRepository.findAll(), HttpStatus.OK);
    }

}