package com.sparta.marketkurlybe.model;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Builder
public class Item extends Timestamped {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

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
    private String delivery;

    @Column
    private String promise;



}
