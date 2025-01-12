package github.dwep1337.updown.service;


import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import github.dwep1337.updown.domain.entity.File;
import github.dwep1337.updown.domain.repositories.FileRepository;
import github.dwep1337.updown.shared.dtos.FileUploadDTO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileUploadService {

    private final MinIOService minIOService;
    private final FileRepository fileRepository;

    public ResponseEntity<?> uploadFile(FileUploadDTO fileUploadDTO) {

        String referenceCode = generateReferenceCode();
        minIOService.uploadFile(fileUploadDTO.file(), referenceCode);

        File file = createFile(fileUploadDTO, referenceCode);

        //save on database
        fileRepository.save(file);

        return ResponseEntity.ok().body(file);
    }

    private File createFile(FileUploadDTO fileUploadDTO, String referenceCode) {
        return File.builder()
                .fileName(fileUploadDTO.file().getOriginalFilename())
                .contentType(fileUploadDTO.file().getContentType())
                .referenceCode(referenceCode)
                .size(fileUploadDTO.file().getSize() / (1024 * 1024)) // Convert bytes to MB
                .build();
    }

    private String generateReferenceCode() {
        return UUID.randomUUID().toString().substring(0, 8);
    }
}
