package com.mancity.user.club.infrastructure.file.util;

import com.amazonaws.AmazonClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class S3Uploader {

    @Autowired
    private AmazonS3 amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.prefix}")
    private String prefix;

    public String uploadEmblem(String domain, MultipartFile file) {
        String image;
        try {
            String fileName = domain + "/" + S3UUIDGenerator.generateUUID();
            initiateMultipartUpload(bucket, fileName, file);
            image = prefix + fileName;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return image;
    }

    private void initiateMultipartUpload(String bucketName, String key, MultipartFile file) throws IOException {
        InitiateMultipartUploadRequest initRequest = new InitiateMultipartUploadRequest(bucketName, key);
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(file.getContentType());
        metadata.setContentLength(file.getSize());
        initRequest.setObjectMetadata(metadata);

        InitiateMultipartUploadResult initResponse = amazonS3Client.initiateMultipartUpload(initRequest);
        String uploadId = initResponse.getUploadId();

        List<PartETag> partETags = new ArrayList<>();
        long contentLength = file.getSize();
        long partSize = 5 * 1024 * 1024; // Set part size to 5 MB.

        try {
            long filePosition = 0;
            for (int i = 1; filePosition < contentLength; i++) {
                partSize = Math.min(partSize, (contentLength - filePosition));

                UploadPartRequest uploadRequest = new UploadPartRequest()
                        .withBucketName(bucketName)
                        .withKey(key)
                        .withUploadId(uploadId)
                        .withPartNumber(i)
                        .withFileOffset(filePosition)
                        .withInputStream(file.getInputStream())
                        .withPartSize(partSize);

                UploadPartResult uploadPartResult = amazonS3Client.uploadPart(uploadRequest);
                partETags.add(uploadPartResult.getPartETag());

                filePosition += partSize;
            }

            CompleteMultipartUploadRequest compRequest = new CompleteMultipartUploadRequest(bucketName, key, uploadId, partETags);
            amazonS3Client.completeMultipartUpload(compRequest);
        } catch (AmazonClientException ex) {
            amazonS3Client.abortMultipartUpload(new AbortMultipartUploadRequest(bucketName, key, uploadId));
            throw ex;
        }
    }
}

