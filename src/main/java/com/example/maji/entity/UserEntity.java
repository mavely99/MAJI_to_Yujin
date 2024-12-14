package com.example.maji.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "USERS")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_IDX")
    private Long userIdx;


    @Column(name = "USER_FN", nullable = false)
    private String userFn;

    @Column(name = "USER_FNY", nullable = false)
    private String userFny;

    @Column(name = "USER_LN", nullable = false)
    private String userLn;

    @Column(name = "USER_LNY", nullable = false)
    private String userLny;

    @Column(name = "USER_ID", nullable = false)
    private String userId;

    @Column(name = "USER_EMAIL", nullable = true)
    private String userEmail;

    @Column(name = "USER_PASS", nullable = false)
    private String userPass;

    @Column(name = "USER_AGE")
    private String userAge;

    @Column(name = "USER_DATE")
    private LocalDateTime userDate;

    @Column(name = "USER_GENDER")
    private String userGender;

    @Column(name = "USER_IMG")
    private String userImg;

    @Column(name = "USER_ADDRESS")
    private String userAddress;

    @Column(name = "USER_PHONE")
    private String userPhone;

    @Column(name = "USER_ROLE")
    private String userRole;

    @Column(name = "USER_POINT")
    private Integer userPoint;

    @Column(name = "USER_STATUSTEXT")
    private String userStatustext;


    // 사용자 이름을 한 번에 설정하는 메소드
    public void setUserName(String firstName, String lastName) {
        this.userFn = firstName;
        this.userLn = lastName;

    }


}
