package com.sparta.marketkurlybe.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Help {
    // ID가 자동으로 생성 및 증가합니다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long helpId;

    @Column
    private Long commentId;

    @Column
    private String userId;

    public Help(Long commentId, String userId) {
        this.commentId = commentId;
        this.userId = userId;
    }
}
