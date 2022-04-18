package com.sparta.marketkurlybe.model;

import com.sparta.marketkurlybe.dto.CommentDto;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Comment {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long commentId;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String comment;

    @Column
    private String title;

//    @Column
//    private String image;
//
//    @Column
//    private int help;

    @ManyToOne
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    public Comment (CommentDto dto){
        this.setUserId(dto.getUserId());
        this.setComment(dto.getComment());
        this.setTitle(dto.getTitle());
    }


}
