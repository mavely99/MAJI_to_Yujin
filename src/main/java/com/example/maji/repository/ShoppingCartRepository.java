package com.example.maji.repository;

import com.example.maji.bean.ContentBean;
import com.example.maji.entity.ShoppingCartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCartEntity, Long> {

    @Query("SELECT s FROM ShoppingCartEntity s " +
            "JOIN FETCH s.userEntity u " +
            "JOIN FETCH s.contentEntity c " +
            "WHERE s.userEntity.userIdx = :userIdx")
    List<ShoppingCartEntity> findUserCartByUserIdx(@Param("userIdx") Long userIdx);


}
