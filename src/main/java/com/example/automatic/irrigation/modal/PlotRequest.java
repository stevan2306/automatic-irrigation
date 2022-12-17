package com.example.automatic.irrigation.modal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlotRequest {
    private String name;
    private IrrigationPlanDetails irrigationPlanDetails;
    private SensorDetails sensorDetails;
}
