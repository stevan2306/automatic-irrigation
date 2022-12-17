package com.example.automatic.irrigation.modal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlotResponse {
    private Integer id;
    private String name;
    private String sensorName;
    private IrrigationPlanDetails irrigationPlan;
    private LocalDateTime lastIrrigatedTime;
}
