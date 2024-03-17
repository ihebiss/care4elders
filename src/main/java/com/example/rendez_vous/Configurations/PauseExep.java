package com.example.rendez_vous.Configurations;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(HttpStatus.CONFLICT)
@Slf4j
public class PauseExep extends RuntimeException {
    public PauseExep(String message) {
        super(message);
        log.info(message);
    }}

