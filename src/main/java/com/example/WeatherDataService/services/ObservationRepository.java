package com.example.WeatherDataService.services;

import com.example.WeatherDataService.models.Observation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ObservationRepository extends JpaRepository<Observation, Integer> {
    // Температура > 0
    @Query("SELECT o FROM Observation o WHERE o.temperature > 0")
    List<Observation> findPositiveTemperature();

    // Присутні опади
    @Query("SELECT o FROM Observation o WHERE o.precipitation IS NOT NULL")
    List<Observation> findWithPrecipitation();
}