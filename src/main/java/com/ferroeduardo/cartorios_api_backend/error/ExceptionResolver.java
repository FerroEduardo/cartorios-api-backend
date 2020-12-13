package com.ferroeduardo.cartorios_api_backend.error;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ferroeduardo.cartorios_api_backend.exception.ApiCustomException;
import com.ferroeduardo.cartorios_api_backend.exception.CartorioNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.TreeMap;

@RestControllerAdvice
public class ExceptionResolver {

    private final Logger logger;

    private final ObjectMapper objectMapper;

    public ExceptionResolver() {
        this.logger = LoggerFactory.getLogger(ExceptionResolver.class);
        this.objectMapper = new ObjectMapper();
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<?> notFound(NoHandlerFoundException exception, HttpServletRequest request) throws JsonProcessingException {
        Map<String, Object> errorMap = new TreeMap<>();
        HttpStatus responseStatus = HttpStatus.NOT_FOUND;
        errorMap.put("message", "Not Found");
        errorMap.put("path", request.getRequestURL().toString());
        errorMap.put("status", responseStatus.value());
        logger.warn(exception.getMessage(), exception);
        String response = objectMapper.writeValueAsString(errorMap);

        return ResponseEntity.status(responseStatus).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @ExceptionHandler(CartorioNotFoundException.class)
    public ResponseEntity<?> cartorioNotFound(CartorioNotFoundException exception, HttpServletRequest request) throws JsonProcessingException {
        Map<String, Object> errorMap = new TreeMap<>();
        HttpStatus responseStatus = HttpStatus.NOT_FOUND;
        errorMap.put("message", "Cartorio Not Found");
        errorMap.put("path", request.getRequestURL().toString());
        errorMap.put("status", responseStatus.value());
        logger.warn(exception.getMessage(), exception);
        String response = objectMapper.writeValueAsString(errorMap);

        return ResponseEntity.status(responseStatus).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @ExceptionHandler(ApiCustomException.class)
    public ResponseEntity<?> apiCustomException(ApiCustomException exception, HttpServletRequest request) throws JsonProcessingException {
        Map<String, Object> errorMap = new TreeMap<>();
        HttpStatus responseStatus = exception.getHttpStatus();
        errorMap.put("message", exception.getMessage());
        errorMap.put("path", request.getRequestURL().toString());
        errorMap.put("status", responseStatus.value());
        logger.warn(exception.getMessage(), exception);
        String response = objectMapper.writeValueAsString(errorMap);

        return ResponseEntity.status(responseStatus).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<?> missingParams(MissingServletRequestParameterException exception, HttpServletRequest request) throws JsonProcessingException {
        Map<String, Object> errorMap = new TreeMap<>();
        HttpStatus responseStatus = HttpStatus.BAD_REQUEST;
        String parameterName = exception.getParameterName();
        String parametertype = exception.getParameterType();
        errorMap.put("message", String.format("O parâmetro '%s'->'%s' está faltando", parametertype, parameterName));
        errorMap.put("path", request.getRequestURL().toString());
        errorMap.put("status", responseStatus.value());
        logger.warn(exception.getMessage(), exception);
        String response = objectMapper.writeValueAsString(errorMap);

        return ResponseEntity.status(responseStatus).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception exception, HttpServletRequest request) throws JsonProcessingException {
        Map<String, Object> errorMap = new TreeMap<>();
        HttpStatus responseStatus = HttpStatus.BAD_REQUEST;
        errorMap.put("message", "Algo estranho deve ter acontecido, tente novamente mais tarde");
        errorMap.put("path", request.getRequestURL().toString());
        errorMap.put("status", responseStatus.value());
        logger.error(exception.getMessage(), exception);
        String response = objectMapper.writeValueAsString(errorMap);

        return ResponseEntity.status(responseStatus).contentType(MediaType.APPLICATION_JSON).body(response);
    }
}
