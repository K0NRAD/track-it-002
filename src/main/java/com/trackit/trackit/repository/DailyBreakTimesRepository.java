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
    @Query(value = "SELECT * FROM daily_break_time INNER JOIN daily_working_hours ON day_break_time.day_break_time_id = daily_working_hours.daily_working_hours_id WHERE daily_working_hours.daily_working_hours_id = ?1 AND daily_working_hours.date=?2", nativeQuery = true)
    List<DailyBreakTimes> getAllBreaksByDailyWorkingHoursIdAndDate(Long dailyWorkingHoursId, LocalDate date);

    @Modifying
    @Transactional
    @Query(value = "UPDATE daily_break_times SET break_check_out=?2 WHERE daily_break_times_id = ?1", nativeQuery = true)
    void breakCheckOut(Long dayBreakTimesId, LocalTime breakCheckOutTime);
}
