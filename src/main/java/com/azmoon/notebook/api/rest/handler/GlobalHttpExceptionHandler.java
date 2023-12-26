package com.azmoon.notebook.api.rest.handler;

import com.azmoon.notebook.api.rest.spec.BaseResponseService;
import com.azmoon.notebook.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RequiredArgsConstructor
@Slf4j
@ControllerAdvice
public class GlobalHttpExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public final ResponseEntity<BaseResponseService> handleBusinessException(BusinessException ex) {
        logger.warn("business error", ex);
        return ResponseEntity.unprocessableEntity().body(new BaseResponseService(ex.getMessage()));
    }
}