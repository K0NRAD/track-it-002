package com.trackit.trackit.model;

import lombok.*;
import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name="DailyWorkingHours")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DailyWorkingHours {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="dailyWorkingHoursId", nullable = false, updatable = false)
    private Long dailyWorkingHoursId;

    @ManyToOne
    @JoinColumn(name="userId")
    @ToString.Exclude
    private User user;

    @Column(name="date", nullable = false, updatable = false)
    private Date date;

    @Column(name="totalDayWorkTime")
    private Time totalDayWorkTime;

    @Column(name="totalBreakTime")
    private Time totalBreakTime;

    @Column(name="checkIn", nullable = false, updatable = false)
    private Time checkIn;

    @Column(name="checkOut")
    private Time checkOut;

    public DailyWorkingHours(User user, Date date, Time checkIn) {
        this.user = user;
        this.date = date;
        this.checkIn = checkIn;
    }
}