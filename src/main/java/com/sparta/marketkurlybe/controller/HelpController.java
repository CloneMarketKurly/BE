package com.sparta.marketkurlybe.controller;


import com.sparta.marketkurlybe.dto.BoardDto;
import com.sparta.marketkurlybe.dto.CommentDto;
import com.sparta.marketkurlybe.security.UserDetailsImpl;
import com.sparta.marketkurlybe.service.HelpService;
import com.sparta.marketkurlybe.validator.ErrorResult;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class HelpController {
    private final HelpService helpService;

    @PostMapping("/item/details/{itemId}/{commentId}/help")
    public Map<String, Object> help(@PathVariable Long itemId, @PathVariable Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        if(userDetails == null){
            return Collections.emptyMap();
        }
        return helpService.help(commentId, itemId, userDetails.getUsername());
    }

    @PostMapping("/item/details/{detailId}/board")
    public ErrorResult createBoard(
            @PathVariable Long detailId,
            @RequestParam("files") List<MultipartFile> files,
            @RequestParam("comment") String comment,
            @AuthenticationPrincipal UserDetailsImpl userDetails){
        if(userDetails == null){
            return new ErrorResult(false, "로그인한 사용자가 없습니다.");
        }
        return helpService.createBoard(detailId, files, comment, userDetails);
    }
}
