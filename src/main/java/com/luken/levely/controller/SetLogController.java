package com.luken.levely.controller;

import com.luken.levely.service.SetLogService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/setlogs")
@RequiredArgsConstructor
public class SetLogController {

    private final SetLogService service;

    @DeleteMapping(value = "/{setLogId}")
    public ResponseEntity<Void> deleteSetLog(@PathVariable UUID setLogId) {
        service.deleteSetLog(setLogId);
        return ResponseEntity.noContent().build();
    }

}