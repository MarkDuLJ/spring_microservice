package com.spring.spring_ms.services;

import com.spring.spring_ms.data.entities.Tour;
import com.spring.spring_ms.data.entities.TourRating;
import com.spring.spring_ms.data.entities.TourRatingPk;
import com.spring.spring_ms.data.repositories.TourRatingRepository;
import com.spring.spring_ms.data.repositories.TourRepository;
import com.spring.spring_ms.web.models.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class TourRatingService {
    private TourRepository tourRepository;
    private TourRatingRepository tourRatingRepository;

    @Autowired
    public TourRatingService(TourRepository tourRepository, TourRatingRepository tourRatingRepository) {
        this.tourRepository = tourRepository;
        this.tourRatingRepository = tourRatingRepository;
    }


    public TourRating createTourRating(int tourId, Rating rating){
        Tour tour = verifyTour(tourId);
        return tourRatingRepository.save(
                new TourRating(
                        new TourRatingPk(tour,rating.getCustomerId()),
                        rating.getScore(),
                        rating.getComment())
        );
    }

    public List<Rating> getAllRatingForTour(int tourId){
        verifyTour(tourId);
        return tourRatingRepository.findByPkTourId(tourId).stream().map(Rating::new).collect(Collectors.toList());
    }

    public Map<String, Double> getRatingAverage(int tourId){
        verifyTour(tourId);
        return Map.of("average",tourRatingRepository.findByPkTourId(tourId).stream().mapToInt(TourRating::getScore).average().orElseThrow(()->new NoSuchElementException("this tour has no ratings yet, id is: " + tourId)));
    }

    public Rating updateWithPut(int tourId, Rating rating){
        TourRating tourRating = verifyTourRating(tourId,rating.getCustomerId());
        tourRating.setScore(rating.getScore());
        tourRating.setComment(rating.getComment());
        return new Rating(tourRatingRepository.save(tourRating));
    }

    public void deleteRating(int tourId, int customerId){
        TourRating rating = verifyTourRating(tourId,customerId);
        tourRatingRepository.delete(rating);
    }

        /**
     * Verify and return the Tour given a tourId.
     *
     * @param tourId tour identifier
     * @return the found Tour
     * @throws NoSuchElementException if no Tour found.
     */
    private Tour verifyTour(int tourId) throws NoSuchElementException {
        return tourRepository.findById(tourId).orElseThrow(() ->
                new NoSuchElementException("Tour does not exist " + tourId));
    }


    private TourRating verifyTourRating(int tourId, int customerId){
        return tourRatingRepository.findByPkTourIdAndPkCustomerId(tourId,customerId).orElseThrow(()->new NoSuchElementException("Tour rating pair for request("+tourId+" for customer "+customerId));
    }


}
