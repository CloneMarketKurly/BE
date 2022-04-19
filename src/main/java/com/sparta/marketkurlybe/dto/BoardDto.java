package com.sparta.marketkurlybe.dto;

import lombok.Getter;

@Getter
public class BoardDto {
    private String userId;
    private Long itemId;
    private String comment;
    private String imageName;
    private String imagePath;
    private Long imageSize;
}
