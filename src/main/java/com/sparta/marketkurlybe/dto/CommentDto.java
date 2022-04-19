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
    private Integer helpCnt;

    private String image;

    private Boolean likeCheck;

    //도움이돼요 -> 뿌려줄 떄 -> 유저가 좋아요 했는지 여부까지(트루, 풀스 여부에 따라 값을 리턴)

    public CommentDto(Comment comment) {
        this.setUserId(comment.getUserId());
        this.setTitle(comment.getTitle());
        this.setComment(comment.getComment());
        this.setCommentId(comment.getCommentId());
//        this.setModifiedAt(comment.getModifiedAt());
        this.setHelpCnt(comment.getHelpCnt());
        this.setImage(comment.getImage());
        this.setLikeCheck(comment.getLikeCheck());
    }
}
