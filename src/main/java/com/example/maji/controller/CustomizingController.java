package com.example.maji.controller;

import com.example.maji.service.CustomizingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/customizing")
public class CustomizingController {

    @Autowired
    private CustomizingService customizingService;



    @GetMapping("/customizing_main")
    public String customizing_main(@RequestParam("customizingInfoIdx") long customizingInfoIdx, Model model) {

        // 모델에 customizingInfoIdx 넘겨주기
        model.addAttribute("customizingInfoIdx", customizingInfoIdx);

        // customizing_main 페이지로 이동
        return "customizing/customizing_main";
    }

    @GetMapping("/customizing_make")
    public String customizing_make(@RequestParam("customizingInfoIdx")long customizingInfoIdx, Model model) {

        // customizingInfoIdx를 기반으로 이름을 찾아서
        String customizingInfoName = customizingService.findCustomizingInfoName(customizingInfoIdx);

        // 모델에 customizingInfoName을 추가
        model.addAttribute("customizingInfoName", customizingInfoName);
        model.addAttribute("customizingInfoIdx", customizingInfoIdx);

        return "customizing/customizing_make";
    }
}
