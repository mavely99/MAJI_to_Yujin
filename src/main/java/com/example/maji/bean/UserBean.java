package com.example.maji.bean;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserBean {

    @NotBlank(message = "ID番号を入力してください")
    private String userId;

    @NotBlank(message = "パスワードを入力してください")
    private String userPass;

    private String userType;

}


