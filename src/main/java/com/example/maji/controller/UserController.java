package com.example.maji.controller;

import com.example.maji.bean.UserBean;
import com.example.maji.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource(name = "loginUserBean")
    private UserBean loginUserBean;

    @Autowired
    private UserService userService;

    //--------------------------------------------

    //회원가입
    @PostMapping("/join_pro")
    public String join_pro(@ModelAttribute("joinUserBean") UserBean joinUserBean) {

        userService.joinUser(joinUserBean);

        return "index";
    }

    //로그인
    @PostMapping("/login_pro")
    public String login_pro(@ModelAttribute("loginUserBean") UserBean loginUserBean,
                            Model model) {

        String userId = loginUserBean.getUserId();
        String userPass = loginUserBean.getUserPass();

        if (userService.authenticate(userId,userPass)) {
            model.addAttribute("message","성공");
            return "main_menu";
        }else {
            model.addAttribute("message", "실패");
            return "user/login";
        }
    }
}