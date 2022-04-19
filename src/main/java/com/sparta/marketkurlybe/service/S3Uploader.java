package com.sparta.marketkurlybe.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;


@Component
@Lazy
public class S3Uploader {

//    @Value("image8292")
//    private String bucket;

    private final AmazonS3 amazonS3;


    public S3Uploader(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    String bucket = "image8292";

    //유저 프로필 사진 업로드
    public String upload(MultipartFile file) throws IOException {
        String fileName = UUID.randomUUID() + "." + StringUtils.getFilenameExtension(file.getOriginalFilename());
        amazonS3.putObject(new PutObjectRequest(bucket, fileName, file.getInputStream(), null)
                .withCannedAcl(CannedAccessControlList.PublicRead));
        return amazonS3.getUrl(bucket, fileName).toString();
    }

    //수정 시, 파일이 바뀌었다면 진행되는 로직
    public String ModifiedProfileImg(String fileName, String userNickName, MultipartFile imgFile) throws IOException {
        amazonS3.deleteObject(new DeleteObjectRequest(bucket, fileName));
        String modifiedFileName = UUID.randomUUID() + "." + StringUtils.getFilenameExtension(imgFile.getOriginalFilename());
        amazonS3.putObject(new PutObjectRequest(bucket, modifiedFileName, imgFile.getInputStream(), null)
                .withCannedAcl(CannedAccessControlList.PublicRead));
        return amazonS3.getUrl(bucket, modifiedFileName).toString();
    }
}
