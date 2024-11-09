package com.example.maji.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "users_seq", allocationSize = 1)
    @Column(name = "user_idx")
    private Long userIdx;

    @Column(name = "user_fn")
    private String userFn;

    @Column(name = "user_fny")
    private String userFny;

    @Column(name = "user_ln")
    private String userLn;

    @Column(name = "user_lny")
    private String userLny;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "user_pass")
    private String userPass;

    @Column(name = "user_age")
    private String userAge;

    @Column(name = "user_date")
    private String userDate;

    @Column(name = "user_gender")
    private String userGender;

    @Column(name = "user_img")
    private String userImg;

    @Column(name = "user_role")
    private String userRole;

    @Column(name = "user_point")
    private String userPoint;


    // 사용자 이름을 한 번에 설정하는 메소드
    public void setUserName(String firstName, String lastName) {
        this.userFn = firstName;
        this.userLn = lastName;
    }
}
