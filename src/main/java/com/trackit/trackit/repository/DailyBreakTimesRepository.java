package com.trackit.trackit.repository;

import com.trackit.trackit.model.DailyBreakTimes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DailyBreakTimesRepository extends JpaRepository<DailyBreakTimes, Long> {
    @Query(value = "SELECT * FROM day_break_time INNER JOIN daily_working_hours ON day_break_time.day_break_time_id = daily_working_hours.daily_working_hours_id WHERE daily_working_hours.daily_working_hours_id = ?1 AND daily_working_hours.date=?2", nativeQuery = true)
    List<DailyBreakTimes> getAllBreaksByDailyWorkingHoursIdAndDate(Long dailyWorkingHoursId, LocalDate date);
}
