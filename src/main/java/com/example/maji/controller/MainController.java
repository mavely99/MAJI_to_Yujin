package com.example.maji.controller;

import com.example.maji.bean.UserBean;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class MainController {

    @Resource(name = "loginUserBean")
    private UserBean loginUserBean;

    //------------------------------------------------------------------

    @GetMapping("/")
    public String main(Model model) {
        return "index_main";
    }

    @GetMapping("/index_main")
    public String index_main(Model model) {
        return "index_main";
    }

    @GetMapping("/need_login")
    public String need_login(Model model) {
        return "alert/need_login";
    }

    @GetMapping("/customizing_main")
    public String customizing_main(Model model) {
        return "customizing/customizing_main";
    }

    @GetMapping("/content_main")
    public String content_main(Model model) {
        return "content/content_main";
    }

    @GetMapping("/myPage_main")
    public String myPage_main(Model model) {
        return "user/myPage_main";
    }

    @GetMapping("/about_main")
    public String about_main(Model model) {
        return "partials/about_main";
    }

}
