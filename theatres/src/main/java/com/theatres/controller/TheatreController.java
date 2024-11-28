package com.theatres.controller;

import com.theatres.model.City;
import com.theatres.exception.*;
import com.theatres.model.Screen;
import com.theatres.model.Theatre;
import com.theatres.services.CityService;
import com.theatres.services.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.theatres.repository.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/theatres")
public class TheatreController {

    @Autowired
    private CityService cityService;

    @Autowired
    private TheatreService theatreService;
    


    @PostMapping("/city")
    public ResponseEntity<?> addCity(@RequestBody City city) {
        try {
            City addedCity = cityService.addCity(city);
            String successMessage = "City " + addedCity.getCityId() + " added successfully";
            Map<String, String> successResponse = new HashMap<>();
            successResponse.put("message", successMessage);
            return ResponseEntity.status(HttpStatus.CREATED).body(successResponse);
        } catch (RuntimeException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Duplicate city");
            errorResponse.put("message", "City already exists");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Internal Server Error");
            errorResponse.put("message", "An unexpected error occurred.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @DeleteMapping("/city/{cityId}")
    public ResponseEntity<?> removeCity(@PathVariable String cityId) {
        try {
            cityService.removeCity(cityId);
        
            String successMessage = "City " + cityId + " is removed successfully";
            Map<String, String> successResponse = new HashMap<>();
            successResponse.put("message", successMessage);

            return ResponseEntity.ok(successResponse);
        } catch (RuntimeException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "City not found");
            errorResponse.put("message", "City: " + cityId + " does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Internal Server Error");
            errorResponse.put("message", "An unexpected error occurred.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @GetMapping("/cities")
    public ResponseEntity<List<City>> getAllCities() {
        List<City> cities = cityService.getAllCities();
        return ResponseEntity.ok(cities);
    }
    
    @PostMapping("/city/{cityName}")
    public ResponseEntity<?> addTheatreInCity(@PathVariable String cityName, @RequestBody Theatre theatre) {
        try {
            Theatre addedTheatre = theatreService.addTheatreInCity(cityName, theatre);
            
            String successMessage = "Theatre " + addedTheatre.getTheatreId() + " is added to " + cityName + " successfully";
            Map<String, String> successResponse = new HashMap<>();
            successResponse.put("message", successMessage);

            return ResponseEntity.status(HttpStatus.CREATED).body(successResponse);
        } catch (CityNotFoundException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "City not found");
            errorResponse.put("message", "City: " + cityName + " does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        } catch (TheatreAlreadyExistsException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Theatre already exists");
            errorResponse.put("message", "Theatre already exists in the city");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        } catch (DataMismatchException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Data Mismatch");
            errorResponse.put("message", "Please provide correct information");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Internal Server Error");
            errorResponse.put("message", "An unexpected error occurred.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
    
    @DeleteMapping("/city/{cityName}/{theatreId}")
    public ResponseEntity<?> removeTheatreInCity(@PathVariable String cityName, @PathVariable String theatreId) {
        try {
            theatreService.removeTheatreInCity(cityName, theatreId);

            String successMessage = "Theatre with ID " + theatreId + " is removed from city " + cityName + " successfully";
            Map<String, String> successResponse = new HashMap<>();
            successResponse.put("message", successMessage);

            return ResponseEntity.ok(successResponse);
        } catch (CityNotFoundException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "City not found");
            errorResponse.put("message", "City not found with name: " + cityName);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(errorResponse);
        } catch (TheatreNotFoundException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Theatre not found in the city");
            errorResponse.put("message", "Theatre not found in the city with ID: " + theatreId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(errorResponse);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Internal Server Error");
            errorResponse.put("message", "An error occurred while removing the theatre");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(errorResponse);
        }
    }


    @GetMapping("/city/{cityName}")
    public ResponseEntity<?> getTheatreInCity(@PathVariable String cityName) {
        try {
            List<Theatre> theatres = theatreService.getTheatresInCity(cityName);
            return ResponseEntity.ok(theatres);
        } catch (CityNotFoundException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "City not found");
            errorResponse.put("message", "City: " + cityName + " does not exists");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(errorResponse);
        }
    }

    @PostMapping("/screen/{theatreId}")
    public ResponseEntity<?> addScreen(@PathVariable String theatreId, @RequestBody Screen screen) {
        try {
            // Validate that the provided theatreId matches the theatreId in the screen
            if (!theatreId.equals(screen.getTheatreId())) {
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("error", "Data Mismatch");
                errorResponse.put("message", "The provided theatreId does not match the theatreId in the screen.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(errorResponse);
            }
            
            theatreService.addScreen(theatreId, screen);
            
            String successMessage = "Screen " + screen.getScreenId() + " is added to Theatre " + theatreId + " successfully";
            Map<String, String> successResponse = new HashMap<>();
            successResponse.put("message", successMessage);

            return ResponseEntity.status(HttpStatus.CREATED).body(successResponse);
        } catch (TheatreNotFoundException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Theatre not found");
            errorResponse.put("message", "Theatre not found with ID: " + theatreId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(errorResponse);
        } catch (RuntimeException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Duplicate Screen");
            errorResponse.put("message", "Screen in Theatre " + theatreId + " already exists");
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(errorResponse);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Internal Server Error");
            errorResponse.put("message", "An unexpected error occurred.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(errorResponse);
        }
    }

    @DeleteMapping("/screen/{theatreId}/{screenId}")
    public ResponseEntity<?> removeScreen(@PathVariable String theatreId, @PathVariable String screenId) {
        try {
            theatreService.removeScreen(theatreId, screenId);
            String successMessage = "Screen with ID " + screenId + " is removed from theatre with ID " + theatreId + " successfully";
            Map<String, String> successResponse = new HashMap<>();
            successResponse.put("message", successMessage);

            return ResponseEntity.ok(successResponse);
        } catch (TheatreNotFoundException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Theatre not found");
            errorResponse.put("message", "Theatre not found with ID: " + theatreId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(errorResponse);
        } catch (ScreenNotFoundException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Screen not found in the theatre");
            errorResponse.put("message", "Screen not found with ID: " + screenId + " in theatre with ID: " + theatreId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(errorResponse);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Internal Server Error");
            errorResponse.put("message", "An unexpected error occurred.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(errorResponse);
        }
    }
    

    
    @GetMapping("/screen/{theatreId}")
    public ResponseEntity<?> getScreenInTheatre(@PathVariable String theatreId) {
        try {
            Iterable<Screen> screensIterable = theatreService.getAllScreensInTheatre(theatreId);
            List<Screen> screens = new ArrayList<>();
            screensIterable.forEach(screens::add);
            return ResponseEntity.ok(screens);
        } catch (TheatreNotFoundException ex) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Theatre not found");
            errorResponse.put("message", "Theatre not found with ID: " + theatreId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(errorResponse);
        } catch (Exception ex) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Internal Server Error");
            errorResponse.put("message", "An unexpected error occurred.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorResponse);
        }
    }
 
    @GetMapping("/screens/{theatreId}")
    public ResponseEntity<?> getScreenCountInTheatre(@PathVariable String theatreId) {
        try {
            int screenCount = theatreService.getScreenCountInTheatre(theatreId);
            return ResponseEntity.ok(screenCount);
        } catch (TheatreNotFoundException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Theatre not found");
            errorResponse.put("message", "Theatre not found with ID: " + theatreId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(errorResponse);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Internal Server Error");
            errorResponse.put("message", "An unexpected error occurred.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorResponse);
        }
    }

}
