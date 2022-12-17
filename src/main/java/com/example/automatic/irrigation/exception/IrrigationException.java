package com.example.automatic.irrigation.exception;

import com.example.automatic.irrigation.modal.ErrorStatus;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class IrrigationException extends RuntimeException {

    private ErrorStatus errorStatus;

    private String description;

    public IrrigationException(Exception e) {
        super(e);
        this.description = e.getMessage();
        this.errorStatus = ErrorStatus.INTERNAL_SERVER_ERROR;
    }

    public IrrigationException(ErrorStatus errorStatus, String description) {
        this.errorStatus = errorStatus;
        this.description = description;
    }
}
