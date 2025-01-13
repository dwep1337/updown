package github.dwep1337.updown.domain.service;


import java.util.UUID;

import github.dwep1337.updown.exception.FileNotFoundException;
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

    public ResponseEntity<File> uploadFile(FileUploadDTO fileUploadDTO) {

        String referenceCode = generateReferenceCode();
        minIOService.uploadFile(fileUploadDTO.file(), referenceCode);

        File file = createFile(fileUploadDTO, referenceCode);

        //save on database
        fileRepository.save(file);

        return ResponseEntity.ok().body(file);
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

        //delete from database
        fileRepository.delete(file);
        //delete from MinIO
        minIOService.deleteFile(referenceCode);
    }
}
