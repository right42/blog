package me.right.study.common.web;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import me.right.study.common.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
@Slf4j
public class ErrorControllerAdvisor {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> businessException(BusinessException e){
        log.error("BusinessException --> " + e);

        ErrorResponse errorResponse = ErrorResponse.of(e.getMessage(), 400);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    private static class ErrorResponse {
        private String code;

        private String message;

        private int status;

        private List<FieldError> errors;

        public static ErrorResponse of(String message, int status){
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.message = message;
            errorResponse.status = status;

            return errorResponse;
        }

        @NoArgsConstructor
        @Getter @Setter
        private class FieldError {
        }

    }
}
