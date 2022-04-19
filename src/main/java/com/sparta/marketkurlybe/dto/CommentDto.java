package com.sparta.marketkurlybe.dto;


import com.sparta.marketkurlybe.model.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class CommentDto {

    private String userId;

    private Long commentId;

    private String comment;

    private String title;

//    private String modifiedAt;
//
//    private Integer helpCnt;

    private String image;


    public CommentDto (Comment comment){
        this.setUserId(comment.getUserId());
        this.setTitle(comment.getTitle());
        this.setComment(comment.getComment());
        this.setCommentId(comment.getCommentId());
//        this.setModifiedAt(comment.getModifiedAt());
//        this.setHelpCnt(comment.getHelpCnt());
        this.setImage(comment.getImage());
    }
}
