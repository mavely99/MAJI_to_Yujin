package com.example.maji.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class ContentBean {

    private Long contentIdx;

    private String contentSubject;

    private String contentText;

    private Date contentDate;

    private int contentViews;

    private Long userIdx;

    private Long goodsInfoIdx;


}
