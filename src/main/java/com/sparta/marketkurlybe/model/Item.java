package com.sparta.marketkurlybe.model;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Builder
public class Item {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String des;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private String detail_Image;

    @Column(nullable = false)
    private String weight;

    @Column(nullable = false)
    private String delivery;

    @Column(nullable = false)
    private String promise;

    @Column(nullable = false)
    private String price;

}
