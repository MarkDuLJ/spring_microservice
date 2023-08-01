package com.spring.spring_ms.web.rest;

import com.spring.spring_ms.data.entities.Tour;
import com.spring.spring_ms.data.entities.TourRating;
import com.spring.spring_ms.data.entities.TourRatingPk;
import com.spring.spring_ms.data.repositories.TourRatingRepository;
import com.spring.spring_ms.data.repositories.TourRepository;
import com.spring.spring_ms.services.TourRatingService;
import com.spring.spring_ms.web.models.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(path = "/tours/{tourId}/ratings")
public class TourRatingController {
    private TourRatingService tourRatingService;

    @Autowired
    public TourRatingController(TourRatingService tourRatingService) {
        this.tourRatingService = tourRatingService;
    }
//
//   private TourRatingRepository tourRatingRepository;
//    private TourRepository tourRepository;

//    @Autowired
//    public TourRatingController(TourRatingRepository tourRatingRepository, TourRepository tourRepository) {
//        this.tourRatingRepository = tourRatingRepository;
//        this.tourRepository = tourRepository;
//    }

    protected TourRatingController() {

    }
//
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public void createTourRating(@PathVariable(value = "tourId") int tourId, @RequestBody @Validated Rating ratingDto) {
//        Tour tour = verifyTour(tourId);
//        tourRatingRepository.save(new TourRating( new TourRatingPk(tour, ratingDto.getCustomerId()),
//                ratingDto.getScore(), ratingDto.getComment()));
//    }
//


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TourRating createRating(@PathVariable("tourId") int tourId, @RequestBody Rating rating ){
       return tourRatingService.createTourRating(tourId, rating);
    }

    @GetMapping
    public List<Rating> getAllRatingForTour(@PathVariable("tourId") int tourId){
       return tourRatingService.getAllRatingForTour(tourId);
    }

    @GetMapping(path ="/average" )
    public Map<String,Double> getRatingAverageForTour(@PathVariable("tourId") int tourId){
        return  tourRatingService.getRatingAverage(tourId);
    }

}
