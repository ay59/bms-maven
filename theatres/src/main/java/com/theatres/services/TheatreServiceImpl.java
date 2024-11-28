package com.theatres.services;
import com.theatres.model.City;
import com.theatres.exception.*;
import com.theatres.model.Screen;
import com.theatres.model.Theatre;
import com.theatres.repository.CityRepository;
import com.theatres.repository.ScreenRepository;
import com.theatres.repository.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service("TheatreService")
public class TheatreServiceImpl implements TheatreService {

    @Autowired
    private TheatreRepository theatreRepository;

    @Autowired
    private CityRepository cityRepository;
    
    @Autowired
    private ScreenRepository screenRepository;

    public Theatre addTheatreInCity(String cityName, Theatre theatre) {
        return cityRepository.findByCityName(cityName)
            .map(city -> {
                if (!city.getCityId().equals(theatre.getCityId())) {
                    throw new DataMismatchException("CityId in the request does not match the city's cityId");
                }
                
                List<String> theatreIds = new ArrayList<>(city.getTheatreIds()); 
                if (theatreIds.contains(theatre.getTheatreId())) {
                    throw new TheatreAlreadyExistsException("Theater already exists in city: " + cityName);
                }

                theatreIds.add(theatre.getTheatreId());
                city.setTheatreIds(theatreIds); 
                cityRepository.save(city);
                theatre.setCityId(city.getCityId());
                return theatreRepository.save(theatre);
            })
            .orElseThrow(() -> new CityNotFoundException("City not found with name: " + cityName));
    }






    @Override
    public void removeTheatreInCity(String cityName, String theatreId) {
        Optional<City> cityOptional = cityRepository.findByCityName(cityName);
        if (cityOptional.isPresent()) {
            City city = cityOptional.get();
            List<String> theatreIds = city.getTheatreIds();

            if (theatreIds.contains(theatreId)) {
                city.getTheatreIds().removeIf(id -> id.equals(theatreId));
                cityRepository.save(city);
                theatreRepository.deleteById(theatreId);
            } else {
                throw new TheatreNotFoundException("Theatre not found with ID: " + theatreId);
            }
        } else {
            throw new CityNotFoundException("City not found with name: " + cityName);
        }
    }

    @Override
    public List<Theatre> getTheatresInCity(String cityName) {
        City city = cityRepository.findByCityName(cityName)
            .orElseThrow(() -> new CityNotFoundException("City not found with name: " + cityName));
        
        List<String> theatreIds = city.getTheatreIds();

        if (!theatreIds.isEmpty()) {
            Iterable<Theatre> theaters = theatreRepository.findAllById(theatreIds);
            return StreamSupport.stream(theaters.spliterator(), false)
                .collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public Screen addScreen(String theatreId, Screen screen) {
        Optional<Theatre> theatreOptional = theatreRepository.findById(theatreId);
        
        if (theatreOptional.isPresent()) {
            Theatre theatre = theatreOptional.get();
            List<String> screenIds = theatre.getScreenIds();

            if (!screenIds.contains(screen.getScreenId())) {
                screenIds.add(screen.getScreenId());
                theatre.setScreenIds(screenIds);
                theatreRepository.save(theatre);

                screen.setTheatreId(theatreId); 
                return screenRepository.save(screen);
            } else {
                throw new ScreenAlreadyExistsException("Screen already exists with ID: " + screen.getScreenId());
            }
        } else {
            throw new TheatreNotFoundException("Theatre not found with ID: " + theatreId);
        }
    }



    @Override
    public void removeScreen(String theatreId, String screenId) {
        Optional<Theatre> theatreOptional = theatreRepository.findById(theatreId);
        if (theatreOptional.isPresent()) {
            Theatre theatre = theatreOptional.get();
            boolean screenRemoved = theatre.getScreenIds().removeIf(id -> id.equals(screenId));
            if (screenRemoved) {
                theatreRepository.save(theatre);
            } else {
                throw new ScreenNotFoundException("Screen not found with ID: " + screenId);
            }
        } else {
            throw new TheatreNotFoundException("Theatre not found with ID: " + theatreId);
        }
    }


    @Override
    public List<Screen> getAllScreensInTheatre(String theatreId) {
        Optional<Theatre> theatreOptional = theatreRepository.findById(theatreId);
        if (theatreOptional.isPresent()) {
            Theatre theatre = theatreOptional.get();
            List<String> screenIds = theatre.getScreenIds();
            
            Iterable<Screen> screensIterable = screenRepository.findAllById(screenIds);
            List<Screen> screens = new ArrayList<>();
            screensIterable.forEach(screens::add);
            
            return screens;
        } else {
            throw new TheatreNotFoundException("Theatre not found with ID: " + theatreId);
        }
    }

    @Override
    public int getScreenCountInTheatre(String theatreId) {
        Optional<Theatre> theatreOptional = theatreRepository.findById(theatreId);
        if (theatreOptional.isPresent()) {
            Theatre theatre = theatreOptional.get();
            List<String> screenIds = theatre.getScreenIds();
            return screenIds.size();
        } else {
            throw new TheatreNotFoundException("Theatre not found with ID: " + theatreId);
        }
    }
}