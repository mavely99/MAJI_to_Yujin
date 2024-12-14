package com.example.maji.controller;

import com.example.maji.bean.CustomizingBean;
import com.example.maji.bean.UserBean;
import com.example.maji.service.CustomizingService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/customizing")
public class CustomizingController {

    private final CustomizingService customizingService;

    @Resource(name = "loginUserBean")
    private UserBean loginUserBean;

    @Autowired
    public CustomizingController(CustomizingService customizingService) {
        this.customizingService = customizingService;
    }

    // 커스터마이징 페이지 이동
    @GetMapping("/customizing_main")
    public String customizing_main(@RequestParam("customizingInfoIdx") long customizingInfoIdx, Model model) {
        model.addAttribute("customizingInfoIdx", customizingInfoIdx);
        return "customizing/customizing_main";
    }
    // 커스터마이징 만들기 페이지로 이동
    @GetMapping("/customizing_make")
    public String customizing_make(@RequestParam("customizingInfoIdx") long customizingInfoIdx, Model model) {

        String customizingInfoName = customizingService.findCustomizingInfoName(customizingInfoIdx);

        model.addAttribute("customizingInfoName", customizingInfoName);
        model.addAttribute("customizingInfoIdx", customizingInfoIdx);
        model.addAttribute("userIdx", loginUserBean.getUserIdx());

        return "customizing/customizing_make";
    }

    //커스터마이징 게시글 저장
    @PostMapping("/save")
    public String customizing_save(@ModelAttribute CustomizingBean customizingBean) {

        customizingService.saveCustomizing(customizingBean);

        return "alert/success_customizing";
    }


}
