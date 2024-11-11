package com.example.maji.controller;

import com.example.maji.bean.UserBean;
import com.example.maji.entity.UserEntity;
import com.example.maji.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.swing.text.html.parser.Entity;

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
    public String join_pro(@ModelAttribute("joinUserBean") UserBean joinUserBean, Model model) {
        try {
            userService.joinUser(joinUserBean);
            return "redirect:/index_main"; // 리다이렉트 사용
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "index_main";
        }
    }

    @PostMapping("/login_pro")
    public String login_pro(@ModelAttribute("loginUserBean") UserBean loginUserBean, RedirectAttributes redirectAttributes) {

        String userId = loginUserBean.getUserId();
        String userPass = loginUserBean.getUserPass();

        if (userService.authenticate(userId, userPass)) {
            redirectAttributes.addFlashAttribute("message", "로그인 성공!");
            return "redirect:/index_main";
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "아이디 또는 비밀번호가 잘못되었습니다.");
            return "redirect:/index_main";
        }
    }

    @GetMapping("/myPage_main")
    public String myPage(Model model) {

        if (!loginUserBean.isUserLogin()) {
            return "redirect:/index_main";
        }

        UserEntity user = userService.getUserByUserId(loginUserBean.getUserId());

        if (user == null) {
            model.addAttribute("message", "User not found."); // 에러 메시지 추가
            return "/user/error"; // 에러 페이지로 이동
        }

        // 사용자 정보를 모델에 추가
        model.addAttribute("user", user);

        return "user/myPage_main"; // 마이페이지 템플릿 반환
    }

    @PostMapping("/updateProfile")
    public String updateProfile(@ModelAttribute UserEntity user, RedirectAttributes redirectAttributes) {

        userService.updateUser(user); // 주입된 userService 사용

        redirectAttributes.addFlashAttribute("message", "プロフィールが更新されました。");
        return "redirect:/user/myPage_main";
    }

    @GetMapping("/logout")
    private String logout(HttpSession session) {
        loginUserBean.setUserLogin(false);
        session.invalidate(); // 세션 무효화
        return "redirect:/index_main";
    }

}
