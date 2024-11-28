package com.example.bookmyshow.controller;

import com.example.bookmyshow.models.ScreenSeat;
import com.example.bookmyshow.models.ShowSeat;
import com.example.bookmyshow.service.ScreenSeatService;
import com.example.bookmyshow.service.ShowSeatService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SeatBookingController {

    private final ShowSeatService showSeatService;

    private final ScreenSeatService screenSeatService;

    public SeatBookingController(ShowSeatService showSeatService, ScreenSeatService screenSeatService) {
        this.showSeatService = showSeatService;
        this.screenSeatService = screenSeatService;
    }

    @GetMapping
    public String Hello(){
    return "Hi";
    }
    @GetMapping("/showSeat")
    public ResponseEntity<List<ShowSeat>> getAllShowSeat() {
        return ResponseEntity.ok().body(showSeatService.getAllShowSeat());
    }

    @GetMapping("/showSeat/{id}")
    public ResponseEntity < ShowSeat > getShowSeatById(@PathVariable String id) {
        return ResponseEntity.ok().body(showSeatService.getShowSeatById(id));
    }

    @PostMapping("/showSeats")
    public ResponseEntity < ShowSeat > createShowSeat(@RequestBody ShowSeat showSeat) {
        return ResponseEntity.ok().body(this.showSeatService.createShowSeat(showSeat));
    }

    @PutMapping("/showSeats/{id}")
    public ResponseEntity < ShowSeat > updateProduct(@PathVariable String id, @RequestBody ShowSeat showSeat) {
        showSeat.setShowId(id);
        return ResponseEntity.ok().body(this.showSeatService.updateShowSeat(showSeat));
    }

    @DeleteMapping("/showSeats/{id}")
    public HttpStatus deleteShowSeat(@PathVariable String id) {
        this.showSeatService.deleteShowSeat(id);
        return HttpStatus.OK;
    }

    @GetMapping("/screenSeat")
    public ResponseEntity<List<ScreenSeat>> getAllScreenSeat() {
        return ResponseEntity.ok().body(screenSeatService.getAllScreenSeat());
    }

    @GetMapping("/screenSeat/{id}")
    public ResponseEntity < ScreenSeat > getScreenSeatById(@PathVariable String id) {
        return ResponseEntity.ok().body(screenSeatService.getScreenSeatById(id));
    }

    @PostMapping("/screenSeat")
    public ResponseEntity < ScreenSeat > createShowSeat(@RequestBody ScreenSeat screenSeat) {
        return ResponseEntity.ok().body(this.screenSeatService.createScreenSeat(screenSeat));
    }

    @PutMapping("/screenSeat/{id}")
    public ResponseEntity < ScreenSeat > updateProduct(@PathVariable String id, @RequestBody ScreenSeat screenSeat) {
        screenSeat.setScreenId(id);
        return ResponseEntity.ok().body(this.screenSeatService.updateScreenSeat(screenSeat));
    }

    @DeleteMapping("/screenSeat/{id}")
    public HttpStatus deleteScreenSeat(@PathVariable String id) {
        this.screenSeatService.deleteScreenSeat(id);
        return HttpStatus.OK;
    }

}
