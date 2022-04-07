package com.trackit.trackit.model;


import lombok.*;
// import org.hibernate.annotations.Table;
import javax.persistence.Entity;
import javax.persistence.*;

@Entity
@Table(name="User")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="userId", nullable=false, updatable = false)
    private Long userId;

    @Column(name="username", nullable = false, updatable = false)
    private String username;

    @Column(name="password", nullable = false, updatable = false)
    private String password;

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }
}