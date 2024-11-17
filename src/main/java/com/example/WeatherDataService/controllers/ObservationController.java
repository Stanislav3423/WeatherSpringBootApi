package com.example.WeatherDataService.controllers;

import com.example.WeatherDataService.models.Observation;
import com.example.WeatherDataService.services.ObservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/observations")
public class ObservationController {
    private final ObservationService observationService;

    @Autowired
    public ObservationController(ObservationService observationService) {
        this.observationService = observationService;
    }
    @GetMapping("/getAll")
    public List<Observation> getObservations() {
        return observationService.getAllObservations();
    }

    @GetMapping("/getPositiveTemperature")
    public List<Observation> getPositiveTemperature() {
        return observationService.getPositiveTemperature();
    }

    @GetMapping("/getWithPrecipitation")
    public List<Observation> getWithPrecipitation() {
        return observationService.getWithPrecipitation();
    }

    @PostMapping("/create")
    public ResponseEntity<Observation> createNewObservation(@RequestBody Observation observation) {
        return observationService.createNewObservation(observation);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Observation> updateObservation(
            @PathVariable Integer id,
            @RequestBody Observation newObservationData) {
        return observationService.editObservation(id, newObservationData);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteObservation(@PathVariable Integer id) {
        observationService.deleteObservation(id);
    }
}
