package com.example.bookmyshow.service;

import com.example.bookmyshow.models.ScreenSeat;

import java.util.List;

public interface ScreenSeatService {
    ScreenSeat createScreenSeat(ScreenSeat screenSeat);

    ScreenSeat updateScreenSeat(ScreenSeat screenSeat);

    List< ScreenSeat > getAllScreenSeat();

    ScreenSeat getScreenSeatById(String screenSeatId);

    void deleteScreenSeat(String id);
}
