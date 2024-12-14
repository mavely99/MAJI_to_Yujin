package com.example.maji.service;

import com.example.maji.bean.ContentBean;
import com.example.maji.bean.UserBean;
import com.example.maji.entity.UserEntity;
import com.example.maji.repository.UserRepository;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Resource(name = "loginUserBean")
    private UserBean loginUserBean;

    //--------------------------------------------

    //회원가입
    public UserEntity joinUser(UserBean joinUserBean) {

        UserEntity userEntity = new UserEntity();

        userEntity.setUserId(joinUserBean.getUserId());
        userEntity.setUserPass(joinUserBean.getUserPass());
        userEntity.setUserFn(joinUserBean.getUserFn());
        userEntity.setUserFny(joinUserBean.getUserFny());
        userEntity.setUserLn(joinUserBean.getUserLn());
        userEntity.setUserLny(joinUserBean.getUserLny());
        userEntity.setUserEmail(joinUserBean.getUserEmail());
        userEntity.setUserPhone(joinUserBean.getUserPhone());
        userEntity.setUserAddress(joinUserBean.getUserAddress());
        userEntity.setUserPoint(500);
        userEntity.setUserRole("user");
        userEntity.setUserDate(LocalDateTime.now());

        return userRepository.save(userEntity);

    }

    //로그인
    public boolean authenticate(String userId, String userPass) {

        Optional<UserEntity> user = userRepository.findByUserId(userId);

        System.out.println(user.get().getUserPhone());

        if (user.isPresent() && user.get().getUserPass().equals(userPass)) {
            UserEntity loginUserEntity = user.get();

            // 로그인 사용자 정보 설정
            loginUserBean.setUserIdx(loginUserEntity.getUserIdx());
            loginUserBean.setUserFn(loginUserEntity.getUserFn());
            loginUserBean.setUserFny(loginUserEntity.getUserFny());
            loginUserBean.setUserLn(loginUserEntity.getUserLn());
            loginUserBean.setUserLny(loginUserEntity.getUserLny());
            loginUserBean.setUserId(loginUserEntity.getUserId());
            loginUserBean.setUserEmail(loginUserEntity.getUserEmail());
            loginUserBean.setUserAddress(loginUserEntity.getUserAddress());
            loginUserBean.setUserDate(loginUserEntity.getUserDate());
            loginUserBean.setUserPhone(loginUserEntity.getUserPhone());
            loginUserBean.setUserRole(loginUserEntity.getUserRole());
            loginUserBean.setUserPoint(loginUserEntity.getUserPoint());
            loginUserBean.setUserLogin(true);

            return true;
        }
        return false;
    }

    public UserEntity getUserByIdx(Long userIdx) {
        // userIdx를 이용해 UserEntity를 조회
        return userRepository.findByUserIdx(userIdx)
                .orElseThrow(() -> new IllegalArgumentException("User not found with idx: " + userIdx));
    }


    public void updateUserInfo(UserBean testBean) {

        UserEntity userEntity = userRepository.findByUserIdx(testBean.getUserIdx())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        // 필드 업데이트 (필요한 값만 변경)
        if (testBean.getUserPhone() != null && !testBean.getUserPhone().isEmpty()) {
            userEntity.setUserPhone(testBean.getUserPhone());
        }
        if (testBean.getUserEmail() != null && !testBean.getUserEmail().isEmpty()) {
            userEntity.setUserEmail(testBean.getUserEmail());
        }
        if (testBean.getUserAddress() != null && !testBean.getUserAddress().isEmpty()) {
            userEntity.setUserAddress(testBean.getUserAddress());
        }

        // 변경 사항 저장
        userRepository.save(userEntity);
    }
   /* @Transactional
    public void updateUser(Long userIdx, String userPhone, String userEmail, String userAddress) {
        System.out.println("Update called for UserIdx: " + userIdx);
        System.out.println("Phone: " + userPhone + ", Email: " + userEmail + ", Address: " + userAddress);


        UserEntity userEntity = userRepository.findByUserIdx(userIdx)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        if (userPhone != null && !userPhone.isEmpty()) {
            userEntity.setUserPhone(userPhone);
        }
        if (userEmail != null && !userEmail.isEmpty()) {
            userEntity.setUserEmail(userEmail);
        }
        if (userAddress != null && !userAddress.isEmpty()) {
            userEntity.setUserAddress(userAddress);
        }

        userRepository.save(userEntity);
        userRepository.flush(); // Hibernate 캐시 강제 반영

    }*/

}