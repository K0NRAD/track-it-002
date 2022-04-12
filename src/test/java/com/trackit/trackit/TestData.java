package com.trackit.trackit;

import com.trackit.trackit.model.DailyBreakTimes;
import com.trackit.trackit.model.DailyWorkingHours;
import com.trackit.trackit.model.Employee;

import java.time.LocalDate;
import java.time.LocalTime;

public class TestData {
    // Test data
    public static Employee employee = new Employee(
            "testUsername",
            "testPassword",
            "testPersonnelNumber",
            "testFirstName",
            "testLastName"
    );
    public static DailyWorkingHours dailyWorkingHours = new DailyWorkingHours(
            employee,
            LocalDate.now(),
            LocalTime.of(8, 30, 0)
    );
    public static DailyBreakTimes breakTime = new DailyBreakTimes(
            dailyWorkingHours,
            LocalTime.of(12, 0, 0)
    );
}
