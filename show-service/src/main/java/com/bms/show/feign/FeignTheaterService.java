package com.bms.show.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bms.show.entity.Theater;

@FeignClient(value = "BMS-THEATER-SERVICE")
public interface FeignTheaterService {

	@GetMapping("theaters/by-ids")
    List<Theater> getTheatersByIds(@RequestParam List<String> theaterIds);
}
