package com.example.maji.bean;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserBean {

    @Column(name = "user_idx", columnDefinition = "NUMBER")
    private Long userIdx;

    @NotBlank(message = "First name cannot be blank")
    private String userFn;

    private String userFny;

    @NotBlank(message = "Last name cannot be blank")
    private String userLn;

    private String userLny;

    private String userId;


    private String userPass;


    private String userEmail;

    @jakarta.validation.constraints.Pattern(regexp = "\\d+", message = "Age must be a number")
    private String userAge;

    private LocalDateTime userDate;

    private String userAddress;

    private String userImg;

    private String userRole;

    private Integer userPoint;

 /*   @jakarta.validation.constraints.Pattern(
            regexp = "^\\+?[1-9]\\d{1,14}$",
            message = "Invalid phone number format"
    )*/
    private String userPhone;

    private boolean userIdExist; //아이디 중복확인 여부

    private boolean userLogin = false; //로그인 상태

    public UserBean() {
        this.userLogin = false;   //userLogin의 초기값은 false
        this.userIdExist = false; //userIdExist의 초기값은 false
    }


}


