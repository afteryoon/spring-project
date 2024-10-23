package com.estsoft.springproject.config.aop;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

   // private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(IllegalArgumentException.class)
    protected ModelAndView illegalArgumentException(
            IllegalArgumentException e,
            Model model
    ) {
        model.addAttribute("error", new ErrorResponse("404","페이지를 찾을 수 없습니다."));
        return new ModelAndView("error/error");
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(
            Exception e
            , Model model
    ) {
        model.addAttribute("error", new ErrorResponse("500","서버에 문제가 생겼습니다."));
        return new ModelAndView("error/error");  // 500 에러 페이지로 이동
    }



    /// ErrorResponse 클래스 (내부 정적 클래스로 정의)
    @Getter @Setter
    public static class ErrorResponse {
        // getters
        private final String code;
        private final String message;

        public ErrorResponse(String code, String message) {
            this.code = code;
            this.message = message;
        }

    }
}
