package com.trackit.trackit.repository;

import com.trackit.trackit.model.DailyWorkingHours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface DailyWorkingHoursRepository extends JpaRepository<DailyWorkingHours, Long> {
    @Query(value = "SELECT * FROM daily_working_hours WHERE employee_id=?1 AND date=?2", nativeQuery = true)
    DailyWorkingHours getDailyWorkingHoursByEmployeeIdAndDate(Long employeeId, LocalDate date);
}