package com.sparta.marketkurlybe.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Builder
public class Basket extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long basketId;

    @OneToMany
    @JoinColumn(name = "buyListId")
    private List<BuyItemList> buyItemList;

    @Column
    private int deliverFee;

    @Column
    private int totalPrice;

    @Column
    private int sumPrice;

}