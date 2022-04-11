package com.trackit.trackit.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.*;

@Entity
@Table(name = "Employee")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "employeeId", nullable = false, updatable = false)
    private Long employeeId;

    @Column(name = "username", nullable = false, updatable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false, updatable = false, unique = true)
    private String password;

    @Column(name = "personnelNumber", nullable = false, updatable = false, unique = true)
    private String personnelNumber;

    @Column(name = "firstName", nullable = false, updatable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false, updatable = false)
    private String lastName;

    public Employee(String username, String password, String personnelNumber, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.personnelNumber = personnelNumber;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
