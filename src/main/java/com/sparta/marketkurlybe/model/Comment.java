package com.sparta.marketkurlybe.model;

import com.sparta.marketkurlybe.dto.CommentDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Comment extends Timestamped{

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long commentId;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String comment;

    @Column
    private String title;

    @Column
    private String image;

    @Column
    private Integer helpCnt = 0;

    @Column
    private Boolean likeCheck;

    @ManyToOne
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    public Comment (CommentDto dto){
        this.setUserId(dto.getUserId());
        this.setComment(dto.getComment());
        this.setTitle(dto.getTitle());
    }


}
