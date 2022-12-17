package com.example.automatic.irrigation.modal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlotResponse {
    private Integer id;
    private String name;
    private String sensorName;
    private IrrigationPlanDetails irrigationPlan;
}
