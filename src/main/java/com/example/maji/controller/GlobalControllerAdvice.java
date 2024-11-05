package com.example.maji.controller;

import com.example.maji.bean.UserBean;
import jakarta.annotation.Resource;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalControllerAdvice {

    @Resource(name = "loginUserBean")
    private UserBean loginUserBean;

    //--------------------------------------------

    @ModelAttribute
    public void addAttributes(Model model) {

        UserBean joinUserBean = new UserBean();

        model.addAttribute("joinUserBean", joinUserBean);
        model.addAttribute("loginUserBean", loginUserBean);
    }

}