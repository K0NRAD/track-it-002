package com.trackit.trackit.service;

import com.trackit.trackit.model.DailyWorkingHours;
import com.trackit.trackit.repository.DailyWorkingHoursRepository;
import com.trackit.trackit.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class DailyWorkingHoursService {
    DailyWorkingHoursRepository dailyWorkingHoursRepository;
    EmployeeRepository employeeRepository;

    public DailyWorkingHoursService(DailyWorkingHoursRepository dailyWorkingHoursRepository, EmployeeRepository employeeRepository){
        this.dailyWorkingHoursRepository = dailyWorkingHoursRepository;
        this.employeeRepository = employeeRepository;
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
}
