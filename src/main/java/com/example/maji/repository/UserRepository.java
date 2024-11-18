package com.example.maji.repository;

import com.example.maji.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUserId(String userId);

    // userIdx 필드로 사용자 조회
    Optional<UserEntity> findByUserIdx(Long userIdx);
}
