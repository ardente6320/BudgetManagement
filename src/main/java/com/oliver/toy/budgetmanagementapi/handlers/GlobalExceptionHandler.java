package com.oliver.toy.budgetmanagementapi.handlers;

import com.oliver.toy.budgetmanagementapi.constants.ApiCode;
import com.oliver.toy.budgetmanagementapi.exceptions.ApiException;
import com.oliver.toy.budgetmanagementapi.utils.ResponseTemplate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    /**
     * ApiException 예외 발생시 클라이언트에게 응답 주기 위한 메소드
     *
     * @param e       ApiException
     * @param request 요청 Object
     * @return
     */
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<?> ApiException(ApiException e, WebRequest request) {
        return new ResponseEntity<>(new ResponseTemplate(e.getApiCode()), HttpStatus.BAD_REQUEST);
    }

    /**
     * NoHandlerFoundException, HttpRequestMethodNotSupportedException, MethodArgumentNotValidException 예외 발생시
     * 클라이언트에게 응답 주기 위한 메소드
     *
     * @return
     */
    @ExceptionHandler({
            NoHandlerFoundException.class,
            HttpRequestMethodNotSupportedException.class,
            MethodArgumentNotValidException.class})
    public ResponseEntity<?> annomalyAccessException(Exception e, WebRequest request) {
        return new ResponseEntity<>(new ResponseTemplate(ApiCode.ANNOMALY_ACCESS), HttpStatus.NOT_FOUND);
    }

    /**
     * WebExchangeBindException, MissingServletRequestParameterException 예외 발생시
     * 클라이언트에게 응답 주기 위한 메소드
     *
     * @param e       Exception
     * @param request 요청 Object
     * @return 
     */
    @ExceptionHandler({WebExchangeBindException.class,
                       MissingServletRequestParameterException.class})
    public ResponseEntity<?> invalidParamException(Exception e, WebRequest request) {
        return new ResponseEntity<>(new ResponseTemplate(ApiCode.INVALID_PARAM), HttpStatus.BAD_REQUEST);
    }

    /**
     * 정의되지 않는 예외 발생시 클라이언트에게 응답 주기 위한 메소드
     *
     * @param e       Exception
     * @param request 요청 Object
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> unexpectedExceptions(Exception e, WebRequest request) {
        return new ResponseEntity<>(new ResponseTemplate(ApiCode.FAILED), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}