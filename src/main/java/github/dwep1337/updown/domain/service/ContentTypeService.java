package github.dwep1337.updown.domain.service;

import github.dwep1337.updown.domain.entity.ContentType;
import github.dwep1337.updown.domain.repositories.ContentTypeRepository;
import github.dwep1337.updown.shared.dtos.create.CreateContentTypeDTO;
import github.dwep1337.updown.shared.dtos.response.ContentTypeResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContentTypeService {

    private final ContentTypeRepository contentTypeRepository;

    public ResponseEntity<Void> addContentType(CreateContentTypeDTO contentType) {

        ContentType content = contentTypeRepository.findByContentType(contentType.contentType());

        if (content == null) {
            contentTypeRepository.save(createContentType(contentType.contentType()));
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.status(409).build();
    }


    private ContentType createContentType(String contentType) {
        return ContentType.builder().contentType(contentType).build();
    }


    public ResponseEntity<List<ContentTypeResponseDTO>> getAllContentTypes() {
        List<ContentTypeResponseDTO> contentTypes = contentTypeRepository
                .findAll().stream().map(contentType ->
                new ContentTypeResponseDTO(contentType.getId(), contentType.getContentType()))
                .toList();

        return ResponseEntity.ok(contentTypes);
    }
}
