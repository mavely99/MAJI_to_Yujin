package com.example.maji.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "customizing_info")  // 대소문자 강제 처리
public class CustomizingEntity {

    @Id
    @Column(name = "customizing_info_idx")  // 대소문자 강제 처리
    private Long customizingInfoIdx;

    @Column(name = "customizing_info_name")  // 대소문자 강제 처리
    private String customizingInfoName;
}
