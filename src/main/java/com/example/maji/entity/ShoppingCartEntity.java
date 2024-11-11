package com.example.maji.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "shopping_cart")
public class ShoppingCartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shopping_cart_seq")
    @SequenceGenerator(name = "shopping_cart_seq", sequenceName = "shopping_cart_seq", allocationSize = 1)
    private Long shoppingCartIdx;

    @Column(name = "user_idx")
    private Long userIdx;

    @Column(name = "content_idx")
    private Long contentIdx;

}
