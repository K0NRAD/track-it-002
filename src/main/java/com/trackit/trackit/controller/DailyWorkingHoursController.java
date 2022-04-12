package com.trackit.trackit.controller;

import com.trackit.trackit.model.DailyWorkingHours;
import com.trackit.trackit.service.DailyWorkingHoursService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequiredArgsConstructor
public class DailyWorkingHoursController {
    @Autowired
    private final DailyWorkingHoursService dailyWorkingHoursService;

    private final DateTimeFormatter localTimeFormatter = DateTimeFormatter.ofPattern("H:mm:ss");
    private final DateTimeFormatter localDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @GetMapping(value = "api/dailyworkinghours/setCheckIn")
    public boolean setCheckIn(
            @RequestParam(value = "employeeId") Long employeeId,
            @RequestParam(value = "checkInTime") String checkInTime
    ) {
        // return true if setting the check in time was successful
        // return false if it wasn't successful
        try{
            LocalTime checkInTimeLocalTime = LocalTime.parse(checkInTime, localTimeFormatter);
            return dailyWorkingHoursService.setCheckIn(employeeId, checkInTimeLocalTime);
        }catch (Exception e){
            System.out.println("Couldn't parse checkInTime to LocalTime. checkInTime given: " + checkInTime);
            System.out.println(e);
            return false;
        }
    }

    @GetMapping(value = "api/dailyworkinghours/getDailyWorkingHoursByEmployeeIdAndDate")
    public DailyWorkingHours getDailyWorkingHoursByEmployeeIdAndDate(
            @RequestParam(value = "employeeId") Long employeeId,
            @RequestParam(value = "date") String date
    ){
        try{
            LocalDate searchDate = LocalDate.parse(date, localDateFormatter);
            System.out.println(employeeId);
            System.out.println(searchDate);
            return dailyWorkingHoursService.getDailyWorkingHoursByEmployeeIdAndDate(employeeId, searchDate);
        }catch(Exception e){
            System.out.println("Couldn't parse the date to LocalDate. Date given: " + date);
            System.out.println(e);
            return null;
        }
    }

    @GetMapping(value = "api/dailyworkinghours/setCheckOut")
    public boolean setCheckOut(
        @RequestParam(value = "dailyWorkingHoursId") Long dailyWorkingHoursId,
        @RequestParam(value = "checkOutTime") String checkOutTime
    ){
        // return true if setting the check in time was successful
        // return false if it wasn't successful
        try{
            LocalTime checkOutLocalTime = LocalTime.parse(checkOutTime, localTimeFormatter);
            return dailyWorkingHoursService.setCheckOut(dailyWorkingHoursId, checkOutLocalTime);
        }catch (Exception e){
            System.out.println("Couldn't parse checkOutTime to LocalTime. checkOutTime given: " + checkOutTime);
            System.out.println(e);
            return false;
        }
    }
}
