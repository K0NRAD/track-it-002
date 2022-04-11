package com.trackit.trackit.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

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
    @JoinColumn(name = "employeeId", nullable = false, updatable = false)
    @ToString.Exclude
    private Employee employee;

    @Column(name = "date", nullable = false, updatable = false)
    private LocalDate date;

    @Column(name = "totalDayWorkTime")
    private LocalTime totalDayWorkTime;

    @Column(name = "totalBreakTime")
    private LocalTime totalBreakTime;

    @Column(name = "checkIn", nullable = false, updatable = false)
    private LocalTime checkIn;

    @Column(name = "checkOut")
    private LocalTime checkOut;

    public DailyWorkingHours(Employee employee, LocalDate date, LocalTime checkIn) {
        this.employee = employee;
        this.date = date;
        this.checkIn = checkIn;
    }
}