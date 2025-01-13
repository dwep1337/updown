package github.dwep1337.updown.domain.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

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

    @SneakyThrows
    public InputStreamResource downloadFile(String referenceCode) {

        var response = s3Client.getObject(GetObjectRequest.builder()
                .bucket(BUCKET_NAME)
                .key(referenceCode)
                .build());

        return new InputStreamResource(response);
    }

    public boolean fileExists(String referenceCode) {
        try {
            s3Client.getObject(GetObjectRequest.builder()
                    .bucket(BUCKET_NAME)
                    .key(referenceCode).build());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @SneakyThrows
    public void deleteFile(String referenceCode) {
        s3Client.deleteObject(DeleteObjectRequest
                .builder().bucket(BUCKET_NAME).key(referenceCode).build());
    }

}
