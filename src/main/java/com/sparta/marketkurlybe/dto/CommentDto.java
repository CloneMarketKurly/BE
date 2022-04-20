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

    private String image;

    //도움이돼요 -> 뿌려줄 떄 -> 유저가 좋아요 했는지 여부까지(트루, 풀스 여부에 따라 값을 리턴)

    public CommentDto (Comment comment){
        this.setUserId(comment.getUserId());
        this.setTitle(comment.getTitle());
        this.setComment(comment.getComment());
        this.setImage(comment.getImage());
    }

}
