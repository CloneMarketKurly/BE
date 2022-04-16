package com.sparta.marketkurlybe.controller;


import com.sparta.marketkurlybe.dto.CommentDto;
import com.sparta.marketkurlybe.repository.CommentRepository;
import com.sparta.marketkurlybe.service.CommentService;
import com.sparta.marketkurlybe.validator.ErrorResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/item/details")
@RestController
public class CommentController {

    private final CommentService commentService;
    private final CommentRepository commentRepository;


    @PostMapping("/{itemId}/comments")
    public ErrorResult newComment (@PathVariable int itemId, @RequestBody CommentDto commentDto) {
        try {
            commentService.commentIn(itemId, commentDto);

            //디테일 넣는거 추가하고 , 레포지토리에 save 해야함.
            return new ErrorResult(true, "후기 등록 완료!");
        } catch (NullPointerException e) {
            log.error("후기등록 에러",e.getMessage());
            return new ErrorResult(false, "후기 등록 실패! ");
        } catch (IllegalArgumentException e){
            log.error("후기등록 에러", e.getMessage());
            return new ErrorResult(false, "일치하는 정보가 없습니다.");
        }
    }

    @PutMapping("/comments/{commentId}")
    public ErrorResult editComment (@PathVariable int commentId, @RequestBody CommentDto commentDto){
        try {
            commentService.updateComment(commentId,commentDto);
            return new ErrorResult(true,"후기 수정 완료!");
        }catch (IllegalArgumentException e){
            log.error("후기수정 에러", e.getMessage());
            return new ErrorResult(false,"일치하는 상품 페이지가 없습니다.");
        }
    }

    @DeleteMapping("/comments/{commentId}")
    public ErrorResult deleteComment(@PathVariable int commentId){
        try {
            commentRepository.delete(commentService.findComment(commentId));
            return new ErrorResult(true, "후기가 삭제되었습니다");
        } catch (IllegalArgumentException e){
            log.error("후기등록 에러", e.getMessage());
            return new ErrorResult(false, "일치하는 후기가 없습니다.");
        }
    }
}
