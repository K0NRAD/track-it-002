package com.trackit.trackit.model;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(name="UserPersonalData")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class UserPersonalData {
    @Id
    @Column(name="personnelNumber", nullable=false, updatable = false)
    private Long personnelNumber;

    @OneToOne
    @JoinColumn(name = "userId")
    private User user;

    @Column(name="firstName", nullable = false, updatable = false)
    private String firstName;

    @Column(name="lastName", nullable = false, updatable = false)
    private String lastName;
}