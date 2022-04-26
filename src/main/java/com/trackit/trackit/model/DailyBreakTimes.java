package com.trackit.trackit.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DailyBreakTimes that = (DailyBreakTimes) o;
        return Objects.equals(dailyBreakTimesId, that.dailyBreakTimesId) && Objects.equals(dailyWorkingHours.getDailyWorkingHoursId(), that.dailyWorkingHours.getDailyWorkingHoursId()) && Objects.equals(breakCheckIn, that.breakCheckIn) && Objects.equals(breakCheckOut, that.breakCheckOut);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dailyBreakTimesId, dailyWorkingHours, breakCheckIn, breakCheckOut);
    }

    public DailyBreakTimes(DailyWorkingHours dailyWorkingHours, LocalTime breakCheckIn){
        this.dailyWorkingHours = dailyWorkingHours;
        this.breakCheckIn = breakCheckIn;
    }
}