package github.dwep1337.updown.controller;

import github.dwep1337.updown.domain.service.ContentTypeService;
import github.dwep1337.updown.shared.dtos.CreateContentTypeDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/content-type")
@RequiredArgsConstructor
public class ContentTypeController {

    private final ContentTypeService contentTypeService;

    @PostMapping()
    public ResponseEntity<Void> addContentType(@Valid @RequestBody CreateContentTypeDTO contentType) {
        return contentTypeService.addContentType(contentType);
    }

}
