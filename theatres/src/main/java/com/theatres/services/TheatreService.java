package com.theatres.services;
import com.theatres.model.Screen;
import com.theatres.model.Theatre;

import java.util.List;

import org.springframework.stereotype.Service;
@Service
public interface TheatreService {

    Theatre addTheatreInCity(String cityName, Theatre theatre);

    void removeTheatreInCity(String cityName, String theatreId);

    List<Theatre> getTheatresInCity(String cityName);

    Screen addScreen(String theatreId, Screen screen);

    void removeScreen(String theatreId, String screenId);

    public Iterable<Screen> getAllScreensInTheatre(String theatreId);

    int getScreenCountInTheatre(String theatreId);

}

