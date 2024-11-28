package com.example.bookmyshow.service;

import com.example.bookmyshow.models.ShowSeat;
import com.example.bookmyshow.repository.ShowSeatRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class ShowSeatServiceImpl implements ShowSeatService {

    private final ShowSeatRepo showSeatRepository;

    public ShowSeatServiceImpl(ShowSeatRepo showSeatRepository) {
        this.showSeatRepository = showSeatRepository;
    }

    @Override
    public ShowSeat createShowSeat(ShowSeat showSeat) {
        return showSeatRepository.save(showSeat);
    }

    @Override
    public ShowSeat updateShowSeat(ShowSeat showSeat) {
        Optional< ShowSeat > showSeatDb = this.showSeatRepository.findById(showSeat.getShowSeatId());

        if (showSeatDb.isPresent()) {
            ShowSeat showSeatUpdate = showSeatDb.get();
            showSeatUpdate.setShowSeatId(showSeat.getShowSeatId());
            showSeatUpdate.setPrice(showSeat.getPrice());
            showSeatUpdate.setStatus(showSeat.getStatus());
            showSeatUpdate.setScreenSeatId(showSeat.getScreenSeatId());
            showSeatUpdate.setShowId(showSeat.getShowId());
            showSeatRepository.save(showSeatUpdate);
            return showSeatUpdate;
        } else {
            throw new ResourceNotFoundException("Record not found with id : " + showSeat.getShowSeatId());
        }
    }

    @Override
    public List<ShowSeat> getAllShowSeat() {
        return (List<ShowSeat>) this.showSeatRepository.findAll();
    }

    @Override
    public ShowSeat getShowSeatById(String showSeatId) {
        Optional < ShowSeat > showSeatDb = this.showSeatRepository.findById(showSeatId);

        if (showSeatDb.isPresent()) {
            return showSeatDb.get();
        } else {
            throw new ResourceNotFoundException("Record not found with id : " + showSeatId);
        }
    }

    @Override
    public void deleteShowSeat(String id) {
        Optional < ShowSeat > showSeatDb = this.showSeatRepository.findById(id);

        if (showSeatDb.isPresent()) {
            this.showSeatRepository.delete(showSeatDb.get());
        } else {
            throw new ResourceNotFoundException("Record not found with id : " + id);
        }
    }
}
