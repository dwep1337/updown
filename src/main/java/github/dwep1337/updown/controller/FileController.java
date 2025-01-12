package github.dwep1337.updown.controller;

import github.dwep1337.updown.service.FileUploadService;
import github.dwep1337.updown.shared.dtos.FileUploadDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileController {

    private final FileUploadService fileUploadService;

    @PostMapping
    public ResponseEntity<?> uploadFile(@Valid @ModelAttribute FileUploadDTO fileUploadDTO) {
        return fileUploadService.uploadFile(fileUploadDTO);
    }
}
