package com.trackit.trackit.repository;

import com.trackit.trackit.model.DailyWorkingHours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;

@Repository
public interface DailyWorkingHoursRepository extends JpaRepository<DailyWorkingHours, Long> {
    //  UPDATE daily_working_hours SET check_out='16:30:00', total_break_time='01:00:00', total_day_work_time='07:00:00' WHERE daily_working_hours_id = 81;
    @Modifying
    @Transactional
    @Query(value = "UPDATE daily_working_hours SET check_out=?1, total_break_time=?2, total_day_work_time=?3 WHERE daily_working_hours_id=?4", nativeQuery = true)
    void updateCheckOutTotalBreakTimeTotalWorkTime(LocalTime checkOutTime, LocalTime totalBreakTime, LocalTime totalDayWorkTime, Long dailyWorkingHoursId);

    @Query(value = "SELECT * FROM daily_working_hours WHERE employee_id=?1 AND date=?2", nativeQuery = true)
    DailyWorkingHours getDailyWorkingHoursByEmployeeIdAndDate(Long employeeId, LocalDate date);
}