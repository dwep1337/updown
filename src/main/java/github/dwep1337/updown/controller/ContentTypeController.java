package github.dwep1337.updown.controller;

import github.dwep1337.updown.domain.service.ContentTypeService;
import github.dwep1337.updown.shared.dtos.create.CreateContentTypeDTO;
import github.dwep1337.updown.shared.dtos.response.ContentTypeResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/content-type")
@RequiredArgsConstructor
public class ContentTypeController {

    private final ContentTypeService contentTypeService;

    @PostMapping()
    public ResponseEntity<Void> addContentType(@Valid @RequestBody CreateContentTypeDTO contentType) {
        return contentTypeService.addContentType(contentType);
    }


    @GetMapping()
    public ResponseEntity<List<ContentTypeResponseDTO>> getAllContentTypes() {
        return contentTypeService.getAllContentTypes();
    }

}
