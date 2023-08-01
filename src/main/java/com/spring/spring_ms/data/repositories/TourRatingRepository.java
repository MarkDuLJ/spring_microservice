package com.spring.spring_ms.data.repositories;

import com.spring.spring_ms.data.entities.TourRating;
import com.spring.spring_ms.data.entities.TourRatingPk;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TourRatingRepository extends CrudRepository<TourRating, TourRatingPk> {
   List<TourRating> findByPkTourId(Integer tourId);
   Optional<TourRating> findByPkTourIdAndPkCustomerId(Integer tourId,Integer customerId);

   Page<TourRating> findByPkTourId(Integer tourId, Pageable pageable);
}
