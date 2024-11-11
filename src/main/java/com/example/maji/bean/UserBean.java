package com.example.maji.bean;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class UserBean {

    private Long userIdx;

    private String userFn;

    private String userFny;

    private String userLn;

    private String userLny;

    private String userId;

    private String userPass;

    private String userEmail;

    private String userAge;

    private Date userDate;

    private String userAddress;

    private String userImg;

    private String userStatustext;

    private String userRole;

    private Long userPoint;

    private String userPhone;

    private boolean userIdExist; //아이디 중복확인 여부

    private boolean userLogin; //로그인 상태

    public UserBean() {
        this.userLogin = false;   //userLogin의 초기값은 false
        this.userIdExist = false; //userIdExist의 초기값은 false
    }


}


