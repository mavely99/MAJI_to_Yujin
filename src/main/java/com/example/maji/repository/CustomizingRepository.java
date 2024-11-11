package com.example.maji.repository;

import com.example.maji.entity.CustomizingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomizingRepository extends JpaRepository<CustomizingEntity, Long> {

    //커스터마이징 게시판 이름 찾기
    Optional<CustomizingEntity> findByCustomizingInfoIdx(Long customizingInfoIdx);



}
