package com.example.WeatherDataService.services;

import com.example.WeatherDataService.models.Observation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ObservationService {
    private final ObservationRepository observationRepository;

    @Autowired
    public ObservationService(ObservationRepository observationRepository) {
        this.observationRepository = observationRepository;
    }
    @Transactional(readOnly = true)
    public List<Observation> getAllObservations() {
        return observationRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Observation> getPositiveTemperature() {
        return observationRepository.findPositiveTemperature();
    }

    @Transactional(readOnly = true)
    public List<Observation> getWithPrecipitation() {
        return observationRepository.findWithPrecipitation();
    }

    @Transactional
    public void deleteObservation(Integer id) {
        observationRepository.deleteById(id);
    }

    @Transactional
    public ResponseEntity<Observation> createNewObservation(Observation observation) {
        System.out.println(observation);
        observationRepository.save(observation);
        return ResponseEntity.ok(observation);
    }

    @Transactional
    public ResponseEntity<Observation> editObservation(Integer id,Observation newObservationData) {
        return observationRepository.findById(id)
                .map(observation -> {
                    observation.setObservationDate(newObservationData.getObservationDate());
                    observation.setTemperature(newObservationData.getTemperature());
                    observation.setHumidity(newObservationData.getHumidity());
                    observation.setWindSpeed(newObservationData.getWindSpeed());
                    observation.setPrecipitation(newObservationData.getPrecipitation());

                    Observation updatedObservation = observationRepository.save(observation);
                    return ResponseEntity.ok(updatedObservation);
                })
                .orElseGet(() -> {
                    return ResponseEntity.notFound().build();
                });
    }

    public int getObservationsByCity(int cityId) {
        return observationRepository.getObservationsByRCity(cityId);
    }
}
