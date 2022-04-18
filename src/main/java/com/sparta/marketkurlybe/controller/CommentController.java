package com.sparta.marketkurlybe.controller;


import com.sparta.marketkurlybe.dto.CommentDto;
import com.sparta.marketkurlybe.model.Comment;
import com.sparta.marketkurlybe.repository.CommentRepository;
import com.sparta.marketkurlybe.security.UserDetailsImpl;
import com.sparta.marketkurlybe.service.CommentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/item/details")
@RestController
public class CommentController {

    private final CommentService commentService;
    private final CommentRepository commentRepository;


//    @PostMapping("/{itemId}/comments")
//    public ErrorResult newComment (@PathVariable Long itemId, @RequestBody CommentDto commentDto) {
//        try {
//            commentService.commentIn(itemId, commentDto);
//
//            return new ErrorResult(true, "후기 등록 완료!");
//        } catch (NullPointerException e) {
//            log.error("후기등록 에러",e.getMessage());
//            return new ErrorResult(false, "후기 등록 실패! ");
//        } catch (IllegalArgumentException e){
//            log.error("후기등록 에러", e.getMessage());
//            return new ErrorResult(false, "일치하는 정보가 없습니다.");
//        }
//    }

    @PostMapping("/{itemId}/comments")
    public Long newComment (@PathVariable Long itemId, @RequestBody CommentDto commentDto) {
            Comment comment = commentService.commentIn(itemId, commentDto);
            return comment.getCommentId();
    }

//    @PutMapping("/comments/{commentId}")
//    public ErrorResult editComment (@PathVariable Long commentId, @RequestBody CommentDto commentDto,
//                                    @AuthenticationPrincipal UserDetailsImpl userDetails){
//        try {
//            commentService.updateComment(commentId,commentDto, userDetails.getUsername());
//            return new ErrorResult(true,"후기 수정 완료!");
//        }catch (IllegalArgumentException e){
//            log.error("후기수정 에러", e.getMessage());
//            return new ErrorResult(false,"일치하는 정보가 없습니다.");
//        }
//    }

    @PutMapping("/comments/{commentId}")
    public ResponseEntity<String> editComment (@PathVariable Long commentId, @RequestBody CommentDto commentDto,
                                    @AuthenticationPrincipal UserDetailsImpl userDetails){
        System.out.println(userDetails.getUsername());
            commentService.updateComment(commentId,commentDto, userDetails.getUsername());
        return ResponseEntity.ok("후기 수정 완료!");
    }


//    @DeleteMapping("/comments/{commentId}")
//    public ErrorResult deleteComment(@PathVariable Long commentId){
//        try {
//            commentRepository.delete(commentService.findComment(commentId));
//            return new ErrorResult(true, "후기가 삭제되었습니다");
//        } catch (IllegalArgumentException e){
//            log.error("후기등록 에러", e.getMessage());
//            return new ErrorResult(false, "일치하는 후기가 없습니다.");
//        }
//    }
    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        System.out.println(commentId);
        System.out.println(userDetails.getUsername());
        commentService.deleteComment(commentId, userDetails.getUsername());
        return ResponseEntity.ok("후기 삭제 완료!");
    }

}
