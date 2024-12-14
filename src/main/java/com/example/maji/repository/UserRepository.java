package com.example.maji.repository;


import com.example.maji.entity.CustomizingEntity;
import com.example.maji.entity.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {


    Optional<UserEntity> findByUserId(String userId);


    // userIdx로 사용자 조회 (자동 생성 메서드)
//    @Query("SELECT u FROM UserEntity u WHERE u.userIdx = :userIdx")
//    Optional<UserEntity> findByUserIdx(@Param("userIdx") Long userIdx);
    Optional<UserEntity> findByUserIdx(Long userIdx);


    @Modifying
    @Transactional
    @Query("UPDATE UserEntity u SET u.userPhone = :userPhone, u.userEmail = :userEmail, u.userAddress = :userAddress WHERE u.userIdx = :userIdx")
    void updateUserInfo(
            @Param("userIdx") Long userIdx,
            @Param("userPhone") String userPhone,
            @Param("userEmail") String userEmail,
            @Param("userAddress") String userAddress
    );
}