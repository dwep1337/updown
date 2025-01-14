package github.dwep1337.updown.controller;

import github.dwep1337.updown.domain.service.DownloadFileService;
import github.dwep1337.updown.domain.service.FileUploadService;
import github.dwep1337.updown.shared.dtos.create.FileUploadDTO;
import github.dwep1337.updown.shared.dtos.response.FileUploadResponseDTO;
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
    public ResponseEntity<FileUploadResponseDTO> uploadFile(
            @Valid @ModelAttribute FileUploadDTO fileUploadDTO) {
        return fileUploadService.uploadFile(fileUploadDTO);
    }

    @GetMapping
    public ResponseEntity<InputStreamResource> downloadFile(
            @RequestParam(value = "download", defaultValue = "") String download) {
        return downloadFileService.downloadFile(download);
    }

    @DeleteMapping("/{referenceCode}")
    public ResponseEntity<Void> deleteFile(@PathVariable String referenceCode) {
        fileUploadService.deleteFile(referenceCode);
        return ResponseEntity.noContent().build();
    }
}
