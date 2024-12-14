package com.example.maji.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "customizing")
@NoArgsConstructor // 파라미터가 없는 디폴트 생성자
@AllArgsConstructor // 모든 필드 값을 파라미터로 받는 생성자
public class CustomizingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customizing_seq")
    @SequenceGenerator(name = "customizing_seq", sequenceName = "customizing_seq", allocationSize = 1)
    private Long customizingIdx;

    @Column(name = "customizing_title", nullable = false)
    private String customizingTitle;

    @Lob // 대용량 텍스트 저장
    @Column(name = "customizing_content", nullable = false)
    private String customizingContent;

    @Column(name = "customizing_date")
    private LocalDateTime customizingDate; //업로드 날짜

    @Column(name = "customizing_img")
    private String customizingImg; //이미지

    @Column(name = "customizing_view_count", columnDefinition = "NUMBER DEFAULT 0")
    private Integer customizingViewCount = 0; //조회수

    @Column(name = "customizing_price")
    private Long customizingPrice;

    @Column(name = "user_idx")
    private Long userIdx;

    @Column(name = "customizing_info_idx")
    private Long customizingInfoIdx;

    //-----------------------------

    @OneToMany(mappedBy = "customizingEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ShoppingCartEntity> shoppingCarts;

    //-----------------------------

    @ManyToOne
    @JoinColumn(name = "user_idx", insertable = false, updatable = false)
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "customizing_info_idx", insertable = false, updatable = false)
    private CustomizingInfoEntity customizingInfoEntity;


}
