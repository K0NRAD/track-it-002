package com.trackit.trackit.repository;

import com.trackit.trackit.model.DailyBreakTimes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface DailyBreakTimesRepository extends JpaRepository<DailyBreakTimes, Long> {
    // SELECT * FROM daily_break_times INNER JOIN daily_working_hours ON daily_break_times.daily_working_hours_id = daily_working_hours.daily_working_hours_id WHERE daily_working_hours.daily_working_hours_id=79 AND daily_working_hours.date='2022-04-12'
    @Query(value = "SELECT * FROM daily_break_times INNER JOIN daily_working_hours ON daily_break_times.daily_working_hours_id = daily_working_hours.daily_working_hours_id WHERE daily_working_hours.daily_working_hours_id = ?1 AND daily_working_hours.date=?2", nativeQuery = true)
    List<DailyBreakTimes> getAllBreaksByDailyWorkingHoursIdAndDate(Long dailyWorkingHoursId, LocalDate date);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "UPDATE daily_break_times SET break_check_out=?2 WHERE daily_break_times_id = ?1", nativeQuery = true)
    void breakCheckOut(Long dayBreakTimesId, LocalTime breakCheckOutTime);

    @Query(value = "SELECT * FROM daily_break_times WHERE daily_working_hours_id=?1 AND break_check_in=?2", nativeQuery = true)
    List<DailyBreakTimes> getDailyBreakTimeByDailyWorkingHoursIdAndBreakCheckIn(Long dailyWorkingHoursId, LocalTime breakCheckIn);
}
