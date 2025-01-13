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

        // check if file is empty 
        if (file == null || file.isEmpty()) {
            addConstraintViolation(context, "File is empty or missing");
            return false;
        }

        String contentType = file.getContentType();

        if (!isSupportedContentType(contentType, context)) { //Check if the file is supported
            addConstraintViolation(context, "Unsupported file type: " + contentType);
            return false;
        }

        return true;
    }

    private boolean isSupportedContentType(String contentType, ConstraintValidatorContext context) {
        var content = contentTypeRepository.findByContentType(contentType);

        if (content == null) {
            addConstraintViolation(context, "Unsupported file type: " + contentType);
            return false;
        }

        return content.getContentType().equals(contentType);
    }

    private void addConstraintViolation(ConstraintValidatorContext context, String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
    }

}
