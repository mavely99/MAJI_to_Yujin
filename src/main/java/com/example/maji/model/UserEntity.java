package com.example.maji.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "test_table")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long userIdx;

//  @Column(name = "userName", nullable = false, length = 50, unique = true)
    private String userName;

    private String userId;

    private String userPass;

    private String userType;

}
