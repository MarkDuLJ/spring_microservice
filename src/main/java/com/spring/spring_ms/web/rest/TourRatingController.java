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
//    /**
//     * Verify and return the Tour given a tourId.
//     *
//     * @param tourId tour identifier
//     * @return the found Tour
//     * @throws NoSuchElementException if no Tour found.
//     */
//    private Tour verifyTour(int tourId) throws NoSuchElementException {
//        return tourRepository.findById(tourId).orElseThrow(() ->
//                new NoSuchElementException("Tour does not exist " + tourId));
//    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TourRating createRating(@PathVariable("tourId") int tourId, @RequestBody Rating rating ){
       return tourRatingService.createTourRating(tourId, rating);
    }

}
