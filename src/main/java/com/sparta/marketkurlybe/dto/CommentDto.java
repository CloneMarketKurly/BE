package com.sparta.marketkurlybe.dto;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CommentDto {


    private String userId;

    private String comment;

    private String title;
//    private String image;
//
//    private int help;

//    private DetailDto detailDto;
}
