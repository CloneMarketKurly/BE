package com.sparta.marketkurlybe.model;

import com.sparta.marketkurlybe.dto.ComResponseDto;
import com.sparta.marketkurlybe.dto.CommentDto;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

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
    private String imageName;

    @Column
    private Integer helpCnt = 0;

    @Column
    @ColumnDefault("false")
    private Boolean helpCheck = false;

    @ManyToOne
//    @JoinColumn(name = "ITEM_ID")
    private Item item;

    public Comment (CommentDto dto){
        this.setUserId(dto.getUserId());
        this.setComment(dto.getComment());
        this.setTitle(dto.getTitle());
    }

    public Comment (ComResponseDto dto){
        this.setUserId(dto.getUserId());
        this.setComment(dto.getComment());
        this.setTitle(dto.getTitle());
    }
}
