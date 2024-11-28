package com.example.bookmyshow.service;

import com.example.bookmyshow.models.ScreenSeat;
import com.example.bookmyshow.repository.ScreenSeatRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class ScreenSeatServiceImpl implements ScreenSeatService {


    private final ScreenSeatRepo screenSeatRepo;

    public ScreenSeatServiceImpl(ScreenSeatRepo screenSeatRepo) {
        this.screenSeatRepo = screenSeatRepo;
    }

    @Override
    public ScreenSeat createScreenSeat(ScreenSeat screenSeat) {
        return screenSeatRepo.save(screenSeat);
    }

    @Override
    public ScreenSeat updateScreenSeat(ScreenSeat screenSeat) {
        Optional<ScreenSeat> screenSeatDb = this.screenSeatRepo.findById(screenSeat.getScreenSeatId());

        if (screenSeatDb.isPresent()) {
            ScreenSeat screenSeatUpdate = screenSeatDb.get();
            screenSeatUpdate.setScreenId(screenSeat.getScreenId());
            screenSeatUpdate.setSeatType(screenSeat.getSeatType());
            screenSeatUpdate.setScreenId(screenSeat.getScreenId());
            screenSeatUpdate.setSeatNum(screenSeat.getSeatNum());
            screenSeatRepo.save(screenSeatUpdate);
            return screenSeatUpdate;
        } else {
            throw new ResourceNotFoundException("Record not found with id : " + screenSeat.getScreenSeatId());
        }
    }

    @Override
    public List<ScreenSeat> getAllScreenSeat() {
        return (List<ScreenSeat>) this.screenSeatRepo.findAll();
    }

    @Override
    public ScreenSeat getScreenSeatById(String screenSeatId) {
        Optional < ScreenSeat > screenSeatDb = this.screenSeatRepo.findById(screenSeatId);

        if (screenSeatDb.isPresent()) {
            return screenSeatDb.get();
        } else {
            throw new ResourceNotFoundException("Record not found with id : " + screenSeatId);
        }
    }

    @Override
    public void deleteScreenSeat(String id) {
        Optional < ScreenSeat > screenSeatDb = this.screenSeatRepo.findById(id);

        if (screenSeatDb.isPresent()) {
            this.screenSeatRepo.delete(screenSeatDb.get());
        } else {
            throw new ResourceNotFoundException("Record not found with id : " + id);
        }
    }
}
