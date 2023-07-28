package com.spring.spring_ms.data.repositories;

import com.spring.spring_ms.data.entities.Tour;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TourRepository extends CrudRepository<Tour, Integer> {
    List<Tour> findByTourPackageCode(String code);
}
