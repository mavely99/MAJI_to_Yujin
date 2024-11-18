package com.example.maji.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "goods_info")
public class GoodsInfoEntity {

    @Id
    private Long goodsInfoIdx;

    @Column(name = "goods_info_name")
    private String goodsInfoName;

    //-----------------------------
    @OneToMany(mappedBy = "goodsInfoEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ContentEntity> contents;

}
