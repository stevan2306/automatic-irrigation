package com.example.automatic.irrigation.controller;

import com.example.automatic.irrigation.exception.IrrigationException;
import com.example.automatic.irrigation.modal.ErrorResponse;
import com.example.automatic.irrigation.modal.ErrorStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class BaseRestController {

    @ExceptionHandler(value = IrrigationException.class)
    public ResponseEntity<ErrorResponse> exception(IrrigationException exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getErrorStatus(), exception.getMessage());
        return ResponseEntity.internalServerError().body(errorResponse);
    }
}
