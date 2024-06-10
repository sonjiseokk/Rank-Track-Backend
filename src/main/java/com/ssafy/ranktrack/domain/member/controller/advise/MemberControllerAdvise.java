package com.ssafy.ranktrack.domain.member.controller.advise;

import com.ssafy.ranktrack.global.exception.DBException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class MemberControllerAdvise {
    @ExceptionHandler(DBException.class)
    public ResponseEntity<?> handleDbException(final DBException e) {
        return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                .body(new Error<>(false, INTERNAL_SERVER_ERROR.value(),e.getMessage()));
    }

    @Data
    @AllArgsConstructor
    static class Error<T> {
        boolean success;
        int code;
        String errorMessage;
    }
}
