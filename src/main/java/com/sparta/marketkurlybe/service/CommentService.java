package com.sparta.marketkurlybe.service;

import com.sparta.marketkurlybe.dto.CommentDto;
import com.sparta.marketkurlybe.model.Comment;
import com.sparta.marketkurlybe.model.Help;
import com.sparta.marketkurlybe.model.Item;
import com.sparta.marketkurlybe.repository.CommentRepository;
import com.sparta.marketkurlybe.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final ItemRepository itemRepository;
    private final CommentRepository commentRepository;
    private final S3Uploader s3Uploader;

    public Comment findComment (Long id){
        return commentRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("일치하는 후기가 없습니다.")
        );
    }

    //사진있을때
    @Transactional
    public Comment commentIn(Long itemId, CommentDto commentDto, String img){
        Item item = itemRepository.findById(itemId).orElseThrow(
                ()-> new IllegalArgumentException("일치하는 상품 상세페이지가 없습니다.")
        );
        Comment comment = new Comment(commentDto);
        comment.setItem(item);
        comment.setImage(img);
        commentRepository.save(comment);
        return comment;
    }

    //사진 없을때
    @Transactional
    public Comment commentIn(Long itemId, CommentDto commentDto){
        Item item = itemRepository.findById(itemId).orElseThrow(
                ()-> new IllegalArgumentException("일치하는 상품 상세페이지가 없습니다.")
        );
        Comment comment = new Comment(commentDto);
        comment.setItem(item);
        commentRepository.save(comment);
        return comment;
    }

    @Transactional
    public void updateComment (Long id, CommentDto commentDto, String userId) {
        Comment comment = findComment(id);
        if (!comment.getUserId().equals(userId)){
            throw new IllegalArgumentException("작성자 본인만 수정 가능합니다.");
        }
        comment.setComment(commentDto.getComment());
        comment.setUserId(commentDto.getUserId());
        comment.setTitle(commentDto.getTitle());

    }

    public void deleteComment (Long id, String userId){
        Comment comment = findComment(id);
        if (!comment.getUserId().equals(userId)){
            throw new IllegalArgumentException("작성자 본인만 삭제가 가능합니다.");
        }
        s3Uploader.deleteImg(comment.getImage());
        commentRepository.delete(comment);
    }


}
