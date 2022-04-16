package com.sparta.marketkurlybe.service;

import com.sparta.marketkurlybe.dto.CommentDto;
import com.sparta.marketkurlybe.model.Comment;
import com.sparta.marketkurlybe.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentService {

//    private final DetailRepository detailRepository;

    private final CommentRepository commentRepository;

    public Comment findComment (int id){
        return commentRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("일치하는 후기가 없습니다.")
        );
    }

//    @Transactional
//    public Comment commentIn(int itemId, CommentDto commentDto){
//        Detail detail = detailRepository.findById(itemId).orElseThrow(
//                ()-> new IllegalArgumentException("일치하는 상품 상세페이지가 없습니다.")
//        );
//        Comment comment = new Comment(commentDto);
//        comment.setDetail(detail);
//        commentRepository.save(comment);
//        return comment;
//    }

    @Transactional
    public void updateComment (int id, CommentDto commentDto) {
        Comment comment = findComment(id);
        comment.setComment(commentDto.getComment());
        comment.setUserName(commentDto.getUserName());

    }



}
