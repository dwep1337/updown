package github.dwep1337.updown.domain.service;

import github.dwep1337.updown.domain.entity.File;
import github.dwep1337.updown.domain.repositories.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DownloadFileService {

    private final FileRepository fileRepository;
    private final MinIOService minIOService;

    public ResponseEntity<InputStreamResource> downloadFile(String referenceCode) {

        File file = fileRepository.findByReferenceCode(referenceCode);

        if (file == null) {
            return ResponseEntity.notFound().build();
        }

        var response = minIOService.downloadFile(referenceCode);

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=\"" + file.getFileName() + "\"")
                .body(new InputStreamResource(response));

    }
}
