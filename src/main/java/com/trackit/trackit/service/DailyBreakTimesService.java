package com.trackit.trackit.service;

import com.trackit.trackit.model.DailyBreakTimes;
import com.trackit.trackit.repository.DailyBreakTimesRepository;
import com.trackit.trackit.repository.DailyWorkingHoursRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.List;

@Service
public class DailyBreakTimesService {
    DailyBreakTimesRepository dailyBreakTimesRepository;
    DailyWorkingHoursRepository dailyWorkingHoursRepository;

    public DailyBreakTimesService(DailyBreakTimesRepository dailyBreakTimesRepository, DailyWorkingHoursRepository dailyWorkingHoursRepository){
        this.dailyBreakTimesRepository = dailyBreakTimesRepository;
        this.dailyWorkingHoursRepository = dailyWorkingHoursRepository;
    }

    public boolean breakCheckIn(Long dailyWorkingHoursId, LocalTime breakCheckInTimeLocalTime) {
        try{
            DailyBreakTimes newBreakTime = new DailyBreakTimes(dailyWorkingHoursRepository.getById(dailyWorkingHoursId), breakCheckInTimeLocalTime);

            dailyBreakTimesRepository.save(newBreakTime);

            return true;
        }catch(Exception e){
            System.out.println("Couldn't set a break check in. DailyWorkingHoursId, breakCheckInTimeLocalTime and Exception:");
            System.out.println(dailyWorkingHoursId);
            System.out.println(breakCheckInTimeLocalTime);
            System.out.println(e);

            return false;
        }
    }

    @Transactional
    public boolean breakCheckOut(Long dayBreakTimesId, LocalTime breakCheckOutTime) {
        try{
            // Get the dayBreakTimesId
            DailyBreakTimes currentBreak = dailyBreakTimesRepository.getById(dayBreakTimesId);
            LocalTime currentBreakCheckOut = currentBreak.getBreakCheckOut();
            LocalTime currentBreakCheckIn = currentBreak.getBreakCheckIn();

            // Make sure that the breakCheckOut time hasn't been set already. You can't check out of your break more than once. You can oly have one single check in and one single check out
            if(currentBreakCheckOut != null){
                return false;
            }

            // Make sure that the breakCheckOut is made later than the breakCheckIn ( breakCheckOut > breakCheckIn ). You can't check in the break at 10:00:00 and check out the break at 09:55:00
            if(
                    breakCheckOutTime.compareTo(currentBreakCheckIn) != 1
            ){
                return false;
            }


            dailyBreakTimesRepository.breakCheckOut(dayBreakTimesId, breakCheckOutTime);
            return true;
        }catch(Exception e){
            System.out.println("Couldn't set the breakCheckOut inside the dailyBreakTimesRepository. dayBreakTimesId, breakCheckOutTime and Exception in order:");
            System.out.println(dayBreakTimesId);
            System.out.println(breakCheckOutTime);
            System.out.println(e);

            return false;
        }
    }

    public List<DailyBreakTimes> getBreakTimesByDailyWorkingHoursId(Long dailyWorkingHoursId) {
        return dailyBreakTimesRepository.getBreakTimesByDailyWorkingHoursId(dailyWorkingHoursId);
    }
}
