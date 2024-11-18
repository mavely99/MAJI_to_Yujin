package com.example.maji.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "content")
public class ContentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "content_seq")
    @SequenceGenerator(name = "content_seq", sequenceName = "content_seq", allocationSize = 1)
    private Long contentIdx;

    @Column(name = "content_subject")
    private String contentSubject;

    @Column(name = "content_text")
    private String contentText;

    @Column(name = "content_date")
    private Date contentDate;

    @Column(name = "content_views")
    private int contentViews;

    //-----------------------------
    @OneToMany(mappedBy = "contentEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ShoppingCartEntity> shoppingCarts;

    //-----------------------------

    @ManyToOne
    @JoinColumn(name = "user_idx", insertable = false, updatable = false)
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "goods_info_idx", insertable = false, updatable = false)
    private GoodsInfoEntity goodsInfoEntity;


}
