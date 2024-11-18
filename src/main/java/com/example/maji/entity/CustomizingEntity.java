package com.example.maji.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "customizing")
@NoArgsConstructor // 파라미터가 없는 디폴트 생성자
@AllArgsConstructor // 모든 필드 값을 파라미터로 받는 생성자
public class CustomizingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "customizing_idx")
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


/*---------------------------JoIn--------------------------------------------------------------------------*/

    // 1. 자기 자신과의 연관 관계 (부모-자식 관계)
    @ManyToOne
    @JoinColumn(name = "parent_customizing_idx")
    private CustomizingEntity parentCustomizing; // 부모 글

    // 2. 사용자 정보와의 연관 관계
    @ManyToOne
    @JoinColumn(name = "user_idx")
    private UserEntity userEntity; // 사용자 정보
}
