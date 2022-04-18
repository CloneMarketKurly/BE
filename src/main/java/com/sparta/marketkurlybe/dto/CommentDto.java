package com.sparta.marketkurlybe.dto;


import com.sparta.marketkurlybe.model.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentDto {


    private String userId;

    private String comment;

    private String title;
//    private String image;
//
//    private int help;

    public CommentDto (Comment comment){
        this.setUserId(comment.getUserId());
        this.setTitle(comment.getTitle());
        this.setComment(comment.getComment());
    }
}
