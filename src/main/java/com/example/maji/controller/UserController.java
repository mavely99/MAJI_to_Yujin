package com.example.maji.controller;

import com.example.maji.bean.UserBean;
import com.example.maji.entity.UserEntity;
import com.example.maji.repository.UserRepository;
import com.example.maji.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/user")
public class UserController {

    @Resource(name = "loginUserBean")
    private UserBean loginUserBean;

    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
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
    public ModelAndView login_pro(@ModelAttribute("loginUserBean") UserBean loginUserBean, RedirectAttributes redirectAttributes) {

        String userId = loginUserBean.getUserId();
        String userPass = loginUserBean.getUserPass();

        if (userService.authenticate(userId, userPass)) {
            redirectAttributes.addFlashAttribute("message", "로그인 성공!");
            return new ModelAndView("redirect:/index_main");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "아이디 또는 비밀번호가 잘못되었습니다.");
            return new ModelAndView("redirect:/index_main");
        }
    }

    @GetMapping("/myPage_main")
    public String myPage(Model model) {

        // 로그인 상태 확인
        if (!loginUserBean.isUserLogin()) {
            return "redirect:/index_main"; // 로그인 페이지로 리다이렉트
        }

        // 로그인된 사용자의 USER_IDX 가져오기
        Long userIdx = loginUserBean.getUserIdx();

        // USER_IDX가 없는 경우 예외 처리
        if (userIdx == null) {
            throw new IllegalStateException("로그인한 사용자의 USER_IDX가 유효하지 않습니다.");
        }
        // 사용자 정보 조회
        UserEntity userEntity = userService.getUserByIdx(userIdx);

        if (userEntity == null) {
            model.addAttribute("errorMessage", "사용자 정보를 찾을 수 없습니다.");
            return "user/myPage_main";
        }


        System.out.println(loginUserBean.getUserAddress());
        System.out.println(loginUserBean.getUserPhone());
        System.out.println(userEntity.getUserFn());
        System.out.println(userEntity.getUserPhone());
        System.out.println(userEntity.getUserAddress());



        // Model에 데이터 추가
        model.addAttribute("user", userEntity);
        model.addAttribute("loginUserBean", loginUserBean);

        return "user/myPage_main"; // 마이페이지 뷰로 이동
    }

//    @PutMapping("/{userIdx}")
//    @ResponseBody
//    public ResponseEntity<?> updateUser(
//            @PathVariable("userIdx") Long userIdx,
//            @RequestBody Map<String, String> updateData) {
//        System.out.println("Received data from client: " + updateData);
//
//        String userPhone = updateData.get("userPhone");
//        String userEmail = updateData.get("userEmail");
//        String userAddress = updateData.get("userAddress");
//
//        if ((userPhone == null || userPhone.isEmpty()) &&
//                (userEmail == null || userEmail.isEmpty()) &&
//                (userAddress == null || userAddress.isEmpty())) {
//            return ResponseEntity.badRequest().body("No data provided to update.");
//        }
//
//        System.out.println("Parsed Data: Phone=" + userPhone + ", Email=" + userEmail + ", Address=" + userAddress);
//
//        try {
//            userService.updateUser(userIdx, userPhone, userEmail, userAddress);
//            return ResponseEntity.ok("Update successful");
//        } catch (Exception e) {
//            return ResponseEntity.internalServerError().body("Failed to update user");
//        }
//    }

    @PostMapping("/update_pro")
    public String updateUser(@ModelAttribute("loginUserBean")UserBean testBean) {

        System.out.println(testBean.getUserPhone());
        System.out.println(testBean.getUserEmail());
        System.out.println(testBean.getUserAddress());

        userService.updateUserInfo(testBean);

        return "redirect:/index_main";
    }

    @GetMapping("/logout")
    private String logout(HttpSession session) {
        loginUserBean.setUserLogin(false);
        session.invalidate(); // 세션 무효화
        return "redirect:/index_main";
    }

}
