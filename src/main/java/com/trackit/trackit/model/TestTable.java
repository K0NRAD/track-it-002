package com.trackit.trackit.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="TestTable")
@NoArgsConstructor
@AllArgsConstructor
public class TestTable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TestTableId", nullable = false, updatable = false)
    private Long id;

    @Column(name = "firstName", nullable = false, updatable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false, updatable = false)
    private String lastName;
}
