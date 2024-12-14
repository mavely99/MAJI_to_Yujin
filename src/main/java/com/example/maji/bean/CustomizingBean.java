package com.example.maji.bean;


import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Getter
@Setter
@Component
public class CustomizingBean {

    private Long customizingIdx;

    private String customizingTitle;

    private String customizingContent;

    private LocalDateTime customizingDate;

    private String customizingImg;

    private Integer customizingViewCount = 0;

    private Long customizingPrice;

    private Long customizingInfoIdx;

    private Long userIdx;
}



