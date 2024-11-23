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

    @ManyToOne
    @JoinColumn(name = "user_idx", insertable = false, updatable = false)
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "customizing_idx", insertable = false, updatable = false)
    private CustomizingEntity customizingEntity;

}
