package com.sparta.marketkurlybe.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Basket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long basketId;

    @OneToOne
    @JoinColumn(name = "userId")
    private User user;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int count;

    //굳이 없어도 될 듯
//    @Column(nullable = false)
//    private int totalPrice;
//
//    @Column(nullable = false)
//    private int deliveryFee;


}
