package com.example.maji.controller;

import com.example.maji.bean.UserBean;
import com.example.maji.service.ShoppingService;
import com.example.maji.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shopping")
public class ShoppingController {

    @Resource(name = "loginUserBean")
    private UserBean loginUserBean;

    @Autowired
    private UserService userService;

    @Autowired
    private ShoppingService shoppingService;

    //--------------------------------------------

    @GetMapping("/cart")
    public String cart() {

        return "shopping/shopping_cart";
    }

    @GetMapping("/payment")
    public String payment(Model model) {
        System.out.println(loginUserBean.getUserFn());
        System.out.println(loginUserBean.getUserAddress());
        System.out.println(loginUserBean.getUserPhone());
        System.out.println(loginUserBean.getUserPoint());
        System.out.println(loginUserBean.getUserRole());
        model.addAttribute("loginUserBean", loginUserBean);
        return "shopping/shopping_payment";
    }
}
