package com.example.rendez_vous.Configurations;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(HttpStatus.NOT_FOUND)
@Slf4j
public class NbrRdvMax extends RuntimeException {
    public NbrRdvMax(String message) {
        super(message);
        log.info(message);
    }}
