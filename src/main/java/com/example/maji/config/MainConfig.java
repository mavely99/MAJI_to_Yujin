package com.example.maji.config;

import com.example.maji.bean.UserBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.context.annotation.SessionScope;

// 시작할 때, 스프링 컨텍스트에 의해 관리되는 bean 으로 자동 등록되게끔
@Configuration
public class MainConfig {

    @Bean("loginUserBean")
    @SessionScope
    @Lazy
    public UserBean loginUserBean() {

        return new UserBean();
    }


}
