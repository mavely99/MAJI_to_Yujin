package com.example.maji.repository;

import com.example.maji.entity.CustomizingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomizingRepository extends JpaRepository<CustomizingEntity, Long> {
}
