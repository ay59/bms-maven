package com.example.bookmyshow.service;

import com.example.bookmyshow.models.ShowSeat;

import java.util.List;

public interface ShowSeatService {
    ShowSeat createShowSeat(ShowSeat showSeat);

    ShowSeat updateShowSeat(ShowSeat showSeat);

    List< ShowSeat > getAllShowSeat();

    ShowSeat getShowSeatById(String showSeatId);

    void deleteShowSeat(String id);
}
