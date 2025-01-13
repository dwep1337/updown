package github.dwep1337.updown.controller;

import github.dwep1337.updown.domain.entity.File;
import github.dwep1337.updown.domain.service.DownloadFileService;
import github.dwep1337.updown.domain.service.FileUploadService;
import github.dwep1337.updown.shared.dtos.FileUploadDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileController {

    private final FileUploadService fileUploadService;
    private final DownloadFileService downloadFileService;

    @PostMapping
    public ResponseEntity<File> uploadFile(@Valid @ModelAttribute FileUploadDTO fileUploadDTO) {
        return fileUploadService.uploadFile(fileUploadDTO);
    }


    @GetMapping("/download/{referenceCode}")
    public ResponseEntity<InputStreamResource> downloadFile(@PathVariable String referenceCode) {
        return downloadFileService.downloadFile(referenceCode);
    }
}
