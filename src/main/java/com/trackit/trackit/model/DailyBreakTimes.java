package com.trackit.trackit.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "DailyBreakTimes")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DailyBreakTimes {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "dailyBreakTimesId", nullable = false, updatable = false)
    private Long dailyBreakTimesId;

    @ManyToOne()
    @JoinColumn(name = "dailyWorkingHoursId")
    @ToString.Exclude
    private DailyWorkingHours dailyWorkingHours;

    @Column(name = "breakCheckIn", nullable = false, updatable = false)
    private LocalTime breakCheckIn;

    @Column(name = "breakCheckOut", updatable = false)
    private LocalTime breakCheckOut;

    public DailyBreakTimes(DailyWorkingHours dailyWorkingHours, LocalTime breakCheckIn){
        this.dailyWorkingHours = dailyWorkingHours;
        this.breakCheckIn = breakCheckIn;
    }
}