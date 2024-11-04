package com.example.maji.service;

import com.example.maji.bean.UserBean;
import com.example.maji.model.UserEntity;
import com.example.maji.repository.UserRepository;
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

}
