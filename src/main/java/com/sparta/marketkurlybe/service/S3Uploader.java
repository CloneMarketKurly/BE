package com.sparta.marketkurlybe.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.sparta.marketkurlybe.dto.CommentDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class S3Uploader {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final AmazonS3 amazonS3;

    public void deleteImg (String fileName){
        log.info("file name : "+ fileName);
        try {
            amazonS3.deleteObject(new DeleteObjectRequest(bucket, fileName));
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
        }
    }


    public String fileNameCh(MultipartFile file){
        String fileName = UUID.randomUUID() + "." + StringUtils.getFilenameExtension(file.getOriginalFilename());
        return fileName;
    }

        //유저 프로필 사진 업로드
    public String upload(MultipartFile file, String fileName) throws IOException {
        amazonS3.putObject(new PutObjectRequest(bucket, fileName, file.getInputStream(), null)
                .withCannedAcl(CannedAccessControlList.PublicRead));
        return amazonS3.getUrl(bucket, fileName).toString();
    }


    //수정 시, 파일이 바뀌었다면 진행되는 로직
    public String ModifiedProfileImg(String fileName, MultipartFile imgFile) throws IOException {
        amazonS3.deleteObject(new DeleteObjectRequest(bucket, fileName));
        String modifiedFileName = UUID.randomUUID() + "." + StringUtils.getFilenameExtension(imgFile.getOriginalFilename());
        amazonS3.putObject(new PutObjectRequest(bucket, modifiedFileName, imgFile.getInputStream(), null)
                .withCannedAcl(CannedAccessControlList.PublicRead));
        return amazonS3.getUrl(bucket, modifiedFileName).toString();
    }


}
