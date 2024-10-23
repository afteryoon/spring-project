package com.estsoft.springproject.config.aop;

//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//@RestControllerAdvice
public class GlobalExceptionHandlerM {


//
//    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
//
//    @ExceptionHandler(IllegalArgumentException.class)
//    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException e) {
//        logger.error("Handling IllegalArgumentException", e);
//        ErrorResponse errorResponse = new ErrorResponse("INVALID_INPUT", e.getMessage());
//        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(EntityNotFoundException.class)
//    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException e) {
//        logger.error("Handling EntityNotFoundException", e);
//        ErrorResponse errorResponse = new ErrorResponse("NOT_FOUND", e.getMessage());
//        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ErrorResponse> handleGenericException(Exception e) {
//        logger.error("Handling unhandled exception", e);
//        ErrorResponse errorResponse = new ErrorResponse("INTERNAL_ERROR", "An unexpected error occurred");
//        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    // ErrorResponse 클래스 (내부 정적 클래스로 정의)
//    public static class ErrorResponse {
//        private final String code;
//        private final String message;
//
//        public ErrorResponse(String code, String message) {
//            this.code = code;
//            this.message = message;
//        }
//
//        // getters
//        public String getCode() { return code; }
//        public String getMessage() { return message; }
//    }
}
