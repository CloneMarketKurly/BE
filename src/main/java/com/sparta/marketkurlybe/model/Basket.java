package com.sparta.marketkurlybe.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
@Getter
@Builder
public class Basket extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long basketId;

    @OneToOne
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany
    private List<BuyItemList> buyItemList;

    @Column
    private int deliverFee;

    @Column
    private int totalPrice;

    @Column
    private int sumPrice;

}