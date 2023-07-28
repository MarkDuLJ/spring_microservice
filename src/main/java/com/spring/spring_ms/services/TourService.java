package com.spring.spring_ms.services;

import com.spring.spring_ms.data.entities.Difficulty;
import com.spring.spring_ms.data.entities.Region;
import com.spring.spring_ms.data.entities.Tour;
import com.spring.spring_ms.data.entities.TourPackage;
import com.spring.spring_ms.data.repositories.TourPackageRepository;
import com.spring.spring_ms.data.repositories.TourRepository;
import com.spring.spring_ms.web.errors.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TourService {
    private TourRepository tourRepository;
    private TourPackageRepository tourPackageRepository;

    @Autowired
    public TourService(TourRepository tourRepository,TourPackageRepository tourPackageRepository){
        this.tourRepository = tourRepository;
        this.tourPackageRepository = tourPackageRepository;
    }

    public Tour createTour(String title, String description, String blurb, Integer price, String duration, String bullets, String keywords, String tourPackageName, Difficulty difficulty, Region region){
        TourPackage tourPackage = tourPackageRepository.findByName(tourPackageName).orElseThrow(()->new NotFoundException("Tour Package not found" + tourPackageName));
        return tourRepository.save(new Tour(title,description,blurb,price,duration,bullets,keywords,tourPackage,difficulty,region));
    }

    public long total(){
        return tourRepository.count();
    }
}
