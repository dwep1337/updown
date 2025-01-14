package github.dwep1337.updown.domain.service;


import github.dwep1337.updown.config.DotEnvConfig;
import github.dwep1337.updown.domain.entity.File;
import github.dwep1337.updown.domain.repositories.FileRepository;
import github.dwep1337.updown.exception.FileNotFoundException;
import github.dwep1337.updown.shared.dtos.create.FileUploadDTO;
import github.dwep1337.updown.shared.dtos.response.FileUploadResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileUploadService {

    private final MinIOService minIOService;
    private final FileRepository fileRepository;


    public ResponseEntity<FileUploadResponseDTO> uploadFile(FileUploadDTO fileUploadDTO) {

        String referenceCode = generateReferenceCode();
        minIOService.uploadFile(fileUploadDTO.file(), referenceCode);

        File file = createFile(fileUploadDTO, referenceCode);

        //save on database
        fileRepository.save(file);

        String downloadUrl = String.format("%s/files/download/%s", DotEnvConfig.dotenv().get("BASE_URL"), referenceCode);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new FileUploadResponseDTO(downloadUrl));
    }

    private File createFile(FileUploadDTO fileUploadDTO, String referenceCode) {
        long bytesToMegabytes = fileUploadDTO.file().getSize() / (1024 * 1024); // Convert bytes to MB

        return File.builder()
                .fileName(fileUploadDTO.file().getOriginalFilename())
                .contentType(fileUploadDTO.file().getContentType())
                .referenceCode(referenceCode)
                .size(bytesToMegabytes)
                .build();
    }

    private String generateReferenceCode() {
        return UUID.randomUUID().toString().substring(0, 8);
    }

    public void deleteFile(String referenceCode) {

        File file = fileRepository.findByReferenceCode(referenceCode);

        if (file == null) {
            throw new FileNotFoundException("File not found");
        }

        fileRepository.delete(file);     //delete from database
        minIOService.deleteFile(referenceCode);    //delete from MinIO
    }
}
