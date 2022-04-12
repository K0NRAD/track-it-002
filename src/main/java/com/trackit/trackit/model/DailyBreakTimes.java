package com.trackit.trackit.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Time;
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
    @Column(name = "dayBreakTimeId", nullable = false, updatable = false)
    private Long dayBreakTimeId;

    @ManyToOne()
    @JoinColumn(name = "dailyWorkingHoursId")
    @ToString.Exclude
    private DailyWorkingHours dailyWorkingHours;

    @Column(name = "breakCheckIn", nullable = false, updatable = false)
    private LocalTime breakCheckIn;

    @Column(name = "breakCheckOut", nullable = false, updatable = false)
    private LocalTime breakCheckOut;
}