package com.sparta.marketkurlybe.service;

import com.sparta.marketkurlybe.dto.BoardDto;
import com.sparta.marketkurlybe.dto.CommentDto;
import com.sparta.marketkurlybe.model.Comment;
import com.sparta.marketkurlybe.model.Help;
import com.sparta.marketkurlybe.repository.CommentRepository;
import com.sparta.marketkurlybe.repository.HelpRepository;
import com.sparta.marketkurlybe.security.UserDetailsImpl;
import com.sparta.marketkurlybe.validator.ErrorResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class HelpService {
    private final HelpRepository helpRepository;
    private final CommentRepository commentRepository;
    private final ItemService itemService;

    @Transactional
    public Map<String, Object> help(Long commentId, Long itemId, String userId) {
        Optional<Help> helpState = helpRepository.findByCommentIdAndUserId(commentId, userId);

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new NullPointerException("후기가 존재하지 않습니다.")
        );

        if(!helpState.isPresent()) {
            Help help = new Help(commentId, userId);
            helpRepository.save(help);
            comment.setHelpCnt(comment.getHelpCnt()+1);
            comment.setHelpCheck(true);
            return itemService.getItemDetails(itemId);
        }

        helpRepository.deleteByCommentIdAndUserId(commentId, userId);
        comment.setHelpCnt(comment.getHelpCnt()-1);
        comment.setHelpCheck(false);
        return itemService.getItemDetails(itemId);
    }
}
