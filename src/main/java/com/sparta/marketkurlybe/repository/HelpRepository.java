package com.sparta.marketkurlybe.repository;

import com.sparta.marketkurlybe.model.Help;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HelpRepository extends JpaRepository<Help, Long> {
    Optional<Help> findByCommentIdAndUserId(Long itemId, String userId);

    void deleteByCommentIdAndUserId(Long itemId, String userId);
}
