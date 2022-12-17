package com.example.automatic.irrigation.modal;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SensorResponse {
    private String plotName;
    private String irrigationStatus;
}
