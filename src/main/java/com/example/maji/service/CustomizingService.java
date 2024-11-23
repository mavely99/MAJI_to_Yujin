package com.example.maji.service;

import com.example.maji.bean.CustomizingBean;
import com.example.maji.entity.CustomizingEntity;
import com.example.maji.entity.CustomizingInfoEntity;
import com.example.maji.repository.CustomizingInfoRepository;
import com.example.maji.repository.CustomizingRepository;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;



import java.time.LocalDateTime;
import java.util.Optional;



@Service
public class CustomizingService {

    @Autowired
    private CustomizingInfoRepository customizingInfoRepository;

    @Autowired
    private CustomizingRepository customizingRepository;

    //커스터마이징 이름 가져오기
    public String findCustomizingInfoName(long customizingInfoIdx) {

        Optional<CustomizingInfoEntity> optionalEntity = customizingInfoRepository.findByCustomizingInfoIdx(customizingInfoIdx);

        return optionalEntity.map(CustomizingInfoEntity::getCustomizingInfoName).orElse("정보 없음");

    }

    //커스터마이징 만들기
    @Transactional
    public void saveCustomizing(CustomizingBean customizingBean) {

        CustomizingEntity customizingEntity = new CustomizingEntity();

        customizingEntity.setCustomizingIdx(customizingBean.getCustomizingIdx());
        customizingEntity.setCustomizingTitle(customizingBean.getCustomizingTitle());
        customizingEntity.setCustomizingContent(customizingBean.getCustomizingContent());
        customizingEntity.setCustomizingDate(LocalDateTime.now());
        customizingEntity.setCustomizingImg(customizingBean.getCustomizingImg());
        customizingEntity.setCustomizingViewCount(customizingBean.getCustomizingViewCount());
        customizingEntity.setCustomizingInfoIdx(customizingBean.getCustomizingInfoIdx());
        customizingEntity.setUserIdx(customizingBean.getUserIdx());

        customizingRepository.save(customizingEntity);
    }

}
