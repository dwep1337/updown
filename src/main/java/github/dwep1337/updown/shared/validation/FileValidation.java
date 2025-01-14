package github.dwep1337.updown.shared.validation;

import github.dwep1337.updown.domain.repositories.ContentTypeRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

public class FileValidation implements ConstraintValidator<IsAllowedContentType, MultipartFile> {

    private final ContentTypeRepository contentTypeRepository;

    public FileValidation(ContentTypeRepository contentTypeRepository) {
        this.contentTypeRepository = contentTypeRepository;
    }

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        if (isFileInvalid(file)) {
            addViolation(context, "File is empty or missing");
            return false;
        }

        if (!isSupportedContentType(file.getContentType())) {
            addViolation(context, "Unsupported file type: " + file.getContentType());
            return false;
        }

        return true;
    }

    private boolean isFileInvalid(MultipartFile file) {
        return file == null || file.isEmpty();
    }

    private boolean isSupportedContentType(String contentType) {
        return contentTypeRepository.findByContentType(contentType) != null;
    }

    private void addViolation(ConstraintValidatorContext context, String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
    }
}
