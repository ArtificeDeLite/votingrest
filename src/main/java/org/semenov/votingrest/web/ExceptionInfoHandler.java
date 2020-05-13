package org.semenov.votingrest.web;

import org.semenov.votingrest.util.exception.IllegalRequestDataException;
import org.semenov.votingrest.util.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

@RestControllerAdvice(annotations = RestController.class)
@Order(Ordered.HIGHEST_PRECEDENCE + 5)
public class ExceptionInfoHandler {
    private static final Logger log = LoggerFactory.getLogger(ExceptionInfoHandler.class);

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> constraintViolationError(HttpServletRequest req, Exception e) {
        log.warn("{} at request  {}: {}", e.getMessage(), req.getRequestURL(), e.getCause() != null ? e.getCause() : e);
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage());
    }


    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> applicationError(HttpServletRequest req, Exception e) {
        log.warn("{} at request  {}: {}", e.getMessage(), req.getRequestURL(), e.getCause() != null ? e.getCause() : e);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public String conflict(HttpServletRequest req, DataIntegrityViolationException e) {

        Throwable result = e;
        Throwable cause;

        while (null != (cause = result.getCause()) && (result != cause)) {
            result = cause;
        }

        String rootMsg = result.getMessage();

        log.warn("{} at request  {}: {}", rootMsg, req.getRequestURL(), e.getCause() != null ? e.getCause() : e);
        return rootMsg;
    }

    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler({BindException.class, MethodArgumentNotValidException.class})
    public String[] bindValidationError(HttpServletRequest req, Exception e) {
        BindingResult result = e instanceof BindException ?
                ((BindException) e).getBindingResult() : ((MethodArgumentNotValidException) e).getBindingResult();

        String[] details = result.getFieldErrors().stream()
                .map(fe -> String.format("[%s] %s", fe.getField(), fe.getDefaultMessage()))
                .toArray(String[]::new);

        log.warn("{} at request  {}: {}", details, req.getRequestURL(), e.getCause() != null ? e.getCause() : e);

        return details;
    }

    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ExceptionHandler({AccessDeniedException.class})
    public String accessDenied(HttpServletRequest req, Exception e) {
        log.warn("{} at request  {}: {}", e.getMessage(), req.getRequestURL(), e.getCause() != null ? e.getCause() : e);
        return e.getMessage();
    }

    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler({IllegalRequestDataException.class, MethodArgumentTypeMismatchException.class, HttpMessageNotReadableException.class})
    public String illegalRequestDataError(HttpServletRequest req, Exception e) {
        log.warn("{} at request  {}: {}", e.getMessage(), req.getRequestURL(), e.getCause() != null ? e.getCause() : e);
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public String handleError(HttpServletRequest req, Exception e) {
        log.warn("{} at request  {}: {}", e.getMessage(), req.getRequestURL(), e.getCause() != null ? e.getCause() : e);
        return e.getMessage();
    }
}