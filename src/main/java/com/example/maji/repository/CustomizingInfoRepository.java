package com.example.maji.repository;

import com.example.maji.entity.CustomizingInfoEntity;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomizingInfoRepository extends JpaRepository<CustomizingInfoEntity, Long> {

    //커스터마이징 게시판 이름 찾기
    Optional<CustomizingInfoEntity> findByCustomizingInfoIdx(Long customizingInfoIdx);

}

