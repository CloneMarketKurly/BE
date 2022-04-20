package com.sparta.marketkurlybe.controller;


import com.sparta.marketkurlybe.service.S3Uploader;
import com.sparta.marketkurlybe.dto.CommentDto;
import com.sparta.marketkurlybe.model.Comment;
import com.sparta.marketkurlybe.security.UserDetailsImpl;
import com.sparta.marketkurlybe.service.CommentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Slf4j
@RequestMapping("/item/details")
@RestController
public class CommentController {


    private final CommentService commentService;
    private final S3Uploader s3Uploader;


//    @PostMapping("/{itemId}/comments")
//    public Long newComment (@PathVariable Long itemId, @RequestBody CommentDto commentDto) {
//            Comment comment = commentService.commentIn(itemId, commentDto);
//            return comment.getCommentId();
//    }


    @PostMapping("/{itemId}/comments")
    public ResponseEntity newComment(@PathVariable Long itemId,
                              @RequestPart(value = "comment") CommentDto commentDto,
                              @RequestPart(value = "image", required = false) MultipartFile imgFile) throws IOException {
        //업로드
        if (imgFile != null) {

            String filename = s3Uploader.fileNameCh(imgFile);
            String img = s3Uploader.upload(imgFile,filename );
            Comment comment = commentService.commentIn(itemId, commentDto,img , filename);

            Map<String, String> com = new HashMap<>();
            com.put("commentID", comment.getCommentId().toString());
            com.put("image", comment.getImage());

            return new ResponseEntity(com, HttpStatus.OK);
        } else {
            Comment comment = commentService.commentIn(itemId, commentDto);
            return new ResponseEntity(comment.getCommentId(), HttpStatus.OK);
        }

    }


    @PutMapping("/comments/{commentId}")
    public ResponseEntity<String> editComment (@PathVariable Long commentId,
                                               @RequestPart(value = "comment") CommentDto commentDto,
                                               @RequestPart(value = "image", required = false) MultipartFile imgFile,
                                               @AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {
        if (imgFile != null) {
            commentService.fileupdateComment(commentId, commentDto,userDetails.getUsername(), imgFile);
            return ResponseEntity.ok("후기 수정 완료!");
        } else {
            commentService.updateComment(commentId,commentDto, userDetails.getUsername());
            return ResponseEntity.ok("후기 수정 완료!");
        }
    }


    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        System.out.println(commentId);
        System.out.println(userDetails.getUsername());
        commentService.deleteComment(commentId, userDetails.getUsername());
        return ResponseEntity.ok("후기 삭제 완료!");
    }

}
