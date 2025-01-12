package github.dwep1337.updown.shared.validation;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FileValidation implements ConstraintValidator<IsAllowedContentType, MultipartFile> {

    //TODO: add these to a database
    private final List<String> supportedContentType = List.of("application/zip");

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {

        // check if file is empty 
        if ( file == null || file.isEmpty() ) {
            addConstraintViolation(context, "File is empty or missing");
            return false;
        }


        //Check if the file is supported
        String contentType = file.getContentType();

        if (!isSupportedContentType(contentType)) {
            addConstraintViolation(context, "Unsupported file type: " + contentType);
            return false;
        }


        return true;  

    }

    private boolean isSupportedContentType(String contentType) {
        return supportedContentType.contains(contentType);
    }

    private void addConstraintViolation(ConstraintValidatorContext context, String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
    }

}
