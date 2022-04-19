package com.sparta.marketkurlybe.controller;


import com.sparta.marketkurlybe.service.S3Uploader;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
@RequestMapping("/item/details")
@RestController
public class CommentController {


    private final CommentService commentService;
    private final CommentRepository commentRepository;
    private final S3Uploader s3Uploader;


//    @PostMapping("/{itemId}/comments")
//    public Long newComment (@PathVariable Long itemId, @RequestBody CommentDto commentDto) {
//            Comment comment = commentService.commentIn(itemId, commentDto);
//            return comment.getCommentId();
//    }


    @PostMapping("/{itemId}/comments")
    public Long newComment11 (@PathVariable Long itemId, @RequestPart(value = "hello") CommentDto commentDto,
                              @RequestPart(value = "image") MultipartFile imgFile) throws IOException {
        //업로드
        System.out.println(itemId);
        String img = s3Uploader.upload(commentDto, imgFile);
        Comment comment = commentService.commentIn(itemId, commentDto,img);
        return comment.getCommentId();
    }


    @PutMapping("/comments/{commentId}")
    public ResponseEntity<String> editComment (@PathVariable Long commentId, @RequestBody CommentDto commentDto,
                                    @AuthenticationPrincipal UserDetailsImpl userDetails){
        System.out.println(userDetails.getUsername());
            commentService.updateComment(commentId,commentDto, userDetails.getUsername());
        return ResponseEntity.ok("후기 수정 완료!");
    }


    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        System.out.println(commentId);
        System.out.println(userDetails.getUsername());
        commentService.deleteComment(commentId, userDetails.getUsername());
        return ResponseEntity.ok("후기 삭제 완료!");
    }




}
