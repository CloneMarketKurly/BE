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
    private int commentId;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String comment;

    @Column
    private String image;

    @Column
    private int help;

    @ManyToOne
    @JoinColumn(name = "DETAIL_ID")
    private Detail detail;

    public Comment (CommentDto dto){
        this.setUserName(dto.getUserName());
        this.setComment(dto.getComment());
    }


}
