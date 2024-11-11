package com.example.maji.service;

import com.example.maji.entity.CustomizingEntity;
import com.example.maji.entity.UserEntity;
import com.example.maji.repository.CustomizingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomizingService {

    @Autowired
    private CustomizingRepository customizingRepository;

    public String findCustomizingInfoName(long customizingInfoIdx) {

        Optional<CustomizingEntity> optionalEntity = customizingRepository.findByCustomizingInfoIdx(customizingInfoIdx);

        return optionalEntity.map(CustomizingEntity::getCustomizingInfoName).orElse("정보 없음");

    }

}
