package com.spring.spring_ms.data.repositories;

import com.spring.spring_ms.data.entities.Tour;
import org.springframework.data.repository.CrudRepository;

public interface TourRepository extends CrudRepository<Tour, Integer> {
}
