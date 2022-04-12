package com.trackit.trackit.controller;

import com.trackit.trackit.service.DailyBreakTimesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequiredArgsConstructor
public class DailyBreakTimesController {
    @Autowired
    private final DailyBreakTimesService dailyBreakTimesService;

    private final DateTimeFormatter localTimeFormatter = DateTimeFormatter.ofPattern("H:mm:ss");

    @GetMapping(value = "api/dailybreaktimes/breakCheckIn")
    public boolean breakCheckIn(
            @RequestParam(value = "dailyWorkingHoursId") Long dailyWorkingHoursId,
            @RequestParam(value = "breakCheckInTime") String breakCheckInTime
    ){
        try{
            LocalTime breakCheckInTimeLocalTime = LocalTime.parse(breakCheckInTime, localTimeFormatter);
            return dailyBreakTimesService.breakCheckIn(dailyWorkingHoursId, breakCheckInTimeLocalTime);
        }catch(Exception e){
            System.out.println("Couldn't parse the LocalTime at breakCheckIn. Time and Exception: ");
            System.out.println(breakCheckInTime);
            System.out.println(e);

            return false;
        }
    }

    @GetMapping(value = "api/dailybreaktimes/breakCheckOut")
    public boolean breakCheckOut(
        @RequestParam(value = "dayBreakTimeId") Long dayBreakTimesId,
        @RequestParam(value = "breakCheckOutTime") String breakCheckOutTime
    ){
        try{
            LocalTime breakCheckOutTimeLocalTime = LocalTime.parse(breakCheckOutTime, localTimeFormatter);
            return dailyBreakTimesService.breakCheckOut(dayBreakTimesId, breakCheckOutTimeLocalTime);
        }catch(Exception e){
            System.out.println("Couldn't parse the LocalTime at breakCheckOut. Time and Exception: ");
            System.out.println(breakCheckOutTime);
            System.out.println(e);

            return false;
        }
    }
}
