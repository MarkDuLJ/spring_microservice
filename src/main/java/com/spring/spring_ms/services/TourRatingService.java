package com.spring.spring_ms.services;

import com.spring.spring_ms.data.entities.Tour;
import com.spring.spring_ms.data.entities.TourRating;
import com.spring.spring_ms.data.entities.TourRatingPk;
import com.spring.spring_ms.data.repositories.TourRatingRepository;
import com.spring.spring_ms.data.repositories.TourRepository;
import com.spring.spring_ms.web.models.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class TourRatingService {
    private TourRepository tourRepository;
    private TourRatingRepository tourRationgRepository;

    @Autowired
    public TourRatingService(TourRepository tourRepository, TourRatingRepository tourRationgRepository) {
        this.tourRepository = tourRepository;
        this.tourRationgRepository = tourRationgRepository;
    }


    public TourRating createTourRating(int tourId, Rating rating){
        Tour tour = tourRepository.findById(tourId).orElseThrow(()->new NoSuchElementException("Tour doesn't exist" + tourId));
        return tourRationgRepository.save(
                new TourRating(
                        new TourRatingPk(tour,rating.getCustomerId()),
                        rating.getScore(),
                        rating.getComment())
        );
    }


}
