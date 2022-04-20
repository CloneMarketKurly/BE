package com.sparta.marketkurlybe.model;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Builder
public class Item extends Timestamped{

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

//    @OneToOne
//    @JoinColumn(name = "buyItemListId")
//    private BuyItemList buyItemList;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private int price;

    @Column
    private String des;

    @Column
    private String detail_Image;

    @Column
    private String weight;

    @Column
    private String packing;

    @Column
    private String promise;
}
