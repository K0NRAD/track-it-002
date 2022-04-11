package com.trackit.trackit.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "DailyWorkingHours")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DailyWorkingHours {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "dailyWorkingHoursId", nullable = false, updatable = false)
    private Long dailyWorkingHoursId;

    @ManyToOne
    @JoinColumn(name = "employeeId")
    @ToString.Exclude
    private Employee employee;

    @Column(name = "date", nullable = false, updatable = false)
    private Date date;

    @Column(name = "totalDayWorkTime")
    private Time totalDayWorkTime;

    @Column(name = "totalBreakTime")
    private Time totalBreakTime;

    @Column(name = "checkIn", nullable = false, updatable = false)
    private Time checkIn;

    @Column(name = "checkOut")
    private Time checkOut;

    public DailyWorkingHours(Employee employee, Date date, Time checkIn) {
        this.employee = employee;
        this.date = date;
        this.checkIn = checkIn;
    }
}