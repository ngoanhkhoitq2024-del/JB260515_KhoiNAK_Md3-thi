package re.edu.md3thi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import re.edu.md3thi.dto.ApiResponse;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<Map<String, String>> handleMethodNotValid(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(
                err -> errors.put(err.getField(), err.getDefaultMessage())
        );
        return new ApiResponse<>(false, "Dữ liệu không hợp lệ", null, errors,
                HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ApiResponse<Void> handleResourceNotFound(ResourceNotFoundException e) {
        return new ApiResponse<>(false, e.getMessage(), null, null,
                HttpStatus.NOT_FOUND.value());
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ApiResponse<Void> handleDuplicateResource(DuplicateResourceException e) {
        return new ApiResponse<>(false, e.getMessage(), null, null,
                HttpStatus.CONFLICT.value());
    }

    @ExceptionHandler(InvalidStatusException.class)
    public ApiResponse<Void> handleInvalidStatus(InvalidStatusException e) {
        return new ApiResponse<>(false, e.getMessage(), null, null,
                HttpStatus.BAD_REQUEST.value());
    }
}
