package com.example.maji.controller;

import com.example.maji.bean.UserBean;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @Resource(name = "loginUserBean")
    private UserBean loginUserBean;

    //--------------------------------------------

    @PostMapping("/login_pro")
    public ModelAndView login_pro(@ModelAttribute("tempLoginUserBean") UserBean tempLoginUserBean, ModelAndView modelAndView) {

        System.out.println(tempLoginUserBean.getUserId());
        System.out.println(tempLoginUserBean.getUserPass());
        System.out.println(tempLoginUserBean.getUserType());

        // 라디오 버튼 값에 따라 페이지 리다이렉트
        if ("学生".equals(tempLoginUserBean.getUserType())) {
            modelAndView.setViewName("redirect:/student_main");
        } else if ("先生".equals(tempLoginUserBean.getUserType())) {
            modelAndView.setViewName("redirect:/professor_main");
        } else if ("管理者".equals(tempLoginUserBean.getUserType())) {
            modelAndView.setViewName("redirect:/administrative_main");
        }

        return modelAndView;
    }
}