package com.trackit.trackit.service;

import com.trackit.trackit.model.DailyBreakTimes;
import com.trackit.trackit.model.DailyWorkingHours;
import com.trackit.trackit.repository.DailyBreakTimesRepository;
import com.trackit.trackit.repository.DailyWorkingHoursRepository;
import com.trackit.trackit.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class DailyWorkingHoursService {
    DailyWorkingHoursRepository dailyWorkingHoursRepository;
    DailyBreakTimesRepository dailyBreakTimesRepository;
    EmployeeRepository employeeRepository;

    public DailyWorkingHoursService(DailyWorkingHoursRepository dailyWorkingHoursRepository, EmployeeRepository employeeRepository, DailyBreakTimesRepository dailyBreakTimesRepository){
        this.dailyWorkingHoursRepository = dailyWorkingHoursRepository;
        this.employeeRepository = employeeRepository;
        this.dailyBreakTimesRepository = dailyBreakTimesRepository;
    }

    public boolean setCheckIn(Long employeeId, LocalTime checkInTime) {
        // return true if the creation of the daily working hours entity has been successful
        // return false otherwise

        // Make sure that the employee hasn't checked in yet today ( can only check in once every day )
        DailyWorkingHours currentDailyWorkingHoursEntityForCurrentDateAndEmployee = dailyWorkingHoursRepository.getDailyWorkingHoursByEmployeeIdAndDate(employeeId, LocalDate.now());

        if(currentDailyWorkingHoursEntityForCurrentDateAndEmployee != null){
            return false;
        }

        // Create new entity and try to save it
        DailyWorkingHours dailyWorkingHours = new DailyWorkingHours(
            employeeRepository.getById(employeeId),
            LocalDate.now(),
            checkInTime
        );

        try{
            dailyWorkingHoursRepository.save(dailyWorkingHours);

            return true;
        }catch(Exception e){
            System.out.println("Couldn't add a new DailyWorkingHours entity");
            System.out.println("Daily Working Hours data:");
            System.out.println(employeeId);
            System.out.println(checkInTime);
            System.out.println(dailyWorkingHours);
            System.out.println(e);

            return false;
        }
    }

    public DailyWorkingHours getDailyWorkingHoursByEmployeeIdAndDate(Long employeeId, LocalDate searchDate) {
        return dailyWorkingHoursRepository.getDailyWorkingHoursByEmployeeIdAndDate(employeeId, searchDate);
    }

    @Transactional
    public boolean setCheckOut(Long dailyWorkingHoursId, Long employeeId, LocalTime checkOutTime) {
        /*
        Properties needed to be updated:
        totalDayWorkTime
        totalBreakTime
        checkOut
        */

        // Get the DailyWorkingHours for employee id and the current date
        DailyWorkingHours currentDailyWorkingHours = dailyWorkingHoursRepository.getById(dailyWorkingHoursId);

        // Get a list of all breaks made today
        List<DailyBreakTimes> dailyBreakTimesForCurrentDate = dailyBreakTimesRepository.getAllBreaksByDailyWorkingHoursIdAndDate(dailyWorkingHoursId, LocalDate.now());

        // Get the totalBreakTime
        LocalTime totalBreakTime = LocalTime.of(0, 0, 0);
        for(DailyBreakTimes breakTimes : dailyBreakTimesForCurrentDate){
            LocalTime breakCheckIn = breakTimes.getBreakCheckIn();
            LocalTime breakCheckOut = breakTimes.getBreakCheckOut();
            LocalTime breakTime;

            // Get the break time
            breakTime = breakCheckOut.minusHours(breakCheckIn.getHour());
            breakTime = breakTime.minusMinutes(breakCheckIn.getMinute());
            breakTime = breakTime.minusSeconds(breakCheckIn.getSecond());

            // Add the break time to the total break time
            totalBreakTime = totalBreakTime.plusHours(breakTime.getHour());
            totalBreakTime = totalBreakTime.plusMinutes(breakTime.getMinute());
            totalBreakTime =  totalBreakTime.plusSeconds(breakTime.getSecond());
        }

        // Get the total work time ( checkOut - checkIn - totalBreakTime )
        LocalTime checkInTime = currentDailyWorkingHours.getCheckIn();
        LocalTime totalDayWorkTime;

        totalDayWorkTime = checkOutTime.minusHours(checkInTime.getHour());
        totalDayWorkTime = totalDayWorkTime.minusMinutes(checkInTime.getMinute());
        totalDayWorkTime = totalDayWorkTime.minusSeconds(checkInTime.getSecond());

        totalDayWorkTime = totalBreakTime.minusHours(totalBreakTime.getHour());
        totalDayWorkTime = totalBreakTime.minusMinutes(totalBreakTime.getMinute());
        totalDayWorkTime = totalBreakTime.minusSeconds(totalBreakTime.getSecond());

        // Save the values
        try{
            DailyWorkingHoursRepository.updateCheckOutTotalBreakTimeTotalWorkTime(checkOutTime, totalBreakTime, totalDayWorkTime, dailyWorkingHoursId);
            return true;
        }catch(Exception e){
            System.out.println("Couldn't update the daily working hours repository with the checkOutTime, totalBreakTime and totalDayWorkTime. Values and exception in order:");
            System.out.println(checkOutTime);
            System.out.println(totalBreakTime);
            System.out.println(totalDayWorkTime);
            System.out.println(e);

            return false;
        }
    }
}
