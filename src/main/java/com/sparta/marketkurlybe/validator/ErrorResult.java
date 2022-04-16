package com.sparta.marketkurlybe.validator;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErrorResult {

    private Boolean ok;
    private String message;
}
