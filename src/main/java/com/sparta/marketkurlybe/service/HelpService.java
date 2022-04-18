package com.sparta.marketkurlybe.service;

import com.sparta.marketkurlybe.model.Help;
import com.sparta.marketkurlybe.repository.HelpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class HelpService {
    private final HelpRepository helpRepository;

    public Boolean help(Long itemId, String userId) {
        Optional<Help> helpState = helpRepository.findByItemIdAndUserId(itemId, userId);
        if(!helpState.isPresent()) {
            Help help = new Help(itemId, userId);
            helpRepository.save(help);
            return true;
        }
        helpRepository.deleteByItemIdAndUserId(itemId, userId);
        return false;
    }
}
