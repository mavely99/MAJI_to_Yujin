package com.example.maji.service;

import com.example.maji.bean.UserBean;
import com.example.maji.entity.UserEntity;
import com.example.maji.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

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

        return userRepository.save(userEntity);

    }

    //로그인
    public boolean authenticate(String userId, String userPass) {
        Optional<UserEntity> user = userRepository.findByUserId(userId);

        return user.isPresent() && user.get().getUserPass().equals(userPass);

    }
    public UserEntity saveName(String firstName, String lastName) {
        UserEntity entity = new UserEntity();
        entity.setUserName(firstName, lastName);
        return userRepository.save(entity);
    }

    public UserEntity getUserByIdx(Long userIdx) {
        // userIdx로 사용자 조회
        return userRepository.findByUserIdx(userIdx)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userIdx));
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

}
