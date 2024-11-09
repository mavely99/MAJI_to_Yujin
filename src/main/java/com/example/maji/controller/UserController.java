package com.example.maji.controller;

import com.example.maji.bean.UserBean;
import com.example.maji.entity.UserEntity;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

        return "index_main";
    }

    //로그인
    @PostMapping("/login_pro")
    public String login_pro(@ModelAttribute("loginUserBean") UserBean loginUserBean,
                            Model model) {

        String userId = loginUserBean.getUserId();
        String userPass = loginUserBean.getUserPass();

        if (userService.authenticate(userId,userPass)) {
            model.addAttribute("message","성공");
            return "index_main";
        }else {
            model.addAttribute("message", "실패");
            return "index_main";
        }
    }
    @GetMapping("/myPage_main")
    public String myPage(Model model) {
        Long userIdx = 952L; // 실제 사용자의 ID
        UserEntity user = userService.getUserByIdx(userIdx); // 사용자 조회

        if (user == null) {
            model.addAttribute("message", "User not found."); // 에러 메시지 추가
            return "error"; // 에러 페이지로 이동
        }

        model.addAttribute("user", user); // 모델에 사용자 추가
        return "user/myPage_main"; // 템플릿 반환
    }
    @PostMapping("/updateProfile")
    public String updateProfile(@ModelAttribute UserEntity user, RedirectAttributes redirectAttributes) {
        userService.updateUser(user);
        redirectAttributes.addFlashAttribute("message", "プロフィールが更新されました。");
        return "redirect:/user/myPage_main";
    }
}