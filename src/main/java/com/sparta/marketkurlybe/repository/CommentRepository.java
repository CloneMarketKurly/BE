package com.sparta.marketkurlybe.repository;

import com.sparta.marketkurlybe.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
