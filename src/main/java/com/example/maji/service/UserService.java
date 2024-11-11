package com.example.maji.service;

import com.example.maji.bean.UserBean;
import com.example.maji.entity.UserEntity;
import com.example.maji.repository.UserRepository;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
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

        UserEntity loginUserEntity = getUserByUserId(userId);

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

        return user.isPresent() && user.get().getUserPass().equals(userPass);

    }

    @Transactional
    public void updateUser(UserEntity updatedUser) {
        UserEntity existingUser = userRepository.findById(updatedUser.getUserIdx())
                .orElseThrow(() -> new IllegalArgumentException("ユーザーが見つかりませんでした。"));

        // 업데이트할 필드 설정
       /* existingUser.setUserEmail(updatedUser.getUserEmail());
        if (updatedUser.getUserPass() != null && !updatedUser.getUserPass().isEmpty()) {
            existingUser.setUserPass(passwordEncoder.encode(updatedUser.getUserPass())); // 비밀번호 해싱
        }
        existingUser.setUserAge(updatedUser.getUserAge());
        existingUser.setUserAddress(updatedUser.getUserAddress());
        existingUser.setUserPhone(updatedUser.getUserPhone());*/

        // 업데이트 후 자동으로 저장됨 (Transactional)
    }

    public UserEntity getUserByUserId(String userId) {
        return userRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));
    }
}
