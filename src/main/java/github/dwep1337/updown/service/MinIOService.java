package github.dwep1337.updown.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.core.sync.RequestBody;

@Service
@RequiredArgsConstructor
public class MinIOService {
    private final static String BUCKET_NAME = "creek-uploads";

     private final S3Client s3Client;


     @SneakyThrows
     public void uploadFile(MultipartFile file, String referenceCode) {

        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(BUCKET_NAME)
                .key(referenceCode)
                .contentType(file.getContentType())
                .build();

        RequestBody requestBody = RequestBody
        .fromInputStream(file.getInputStream(), file.getSize());

        s3Client.putObject(request, requestBody);
     }


}
