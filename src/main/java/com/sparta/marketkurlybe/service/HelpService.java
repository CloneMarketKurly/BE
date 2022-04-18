package com.sparta.marketkurlybe.service;

import com.sparta.marketkurlybe.model.Help;
import com.sparta.marketkurlybe.repository.HelpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class HelpService {
    private final HelpRepository helpRepository;

    @Transactional
    public Boolean help(Long commentId, String userId) {
        Optional<Help> helpState = helpRepository.findByCommentIdAndUserId(commentId, userId);
        if(!helpState.isPresent()) {
            Help help = new Help(commentId, userId);
            helpRepository.save(help);
            return true;
        }
        helpRepository.deleteByCommentIdAndUserId(commentId, userId);
        return false;
    }
}
