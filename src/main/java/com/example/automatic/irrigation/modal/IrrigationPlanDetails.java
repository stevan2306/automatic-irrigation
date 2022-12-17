package com.example.automatic.irrigation.modal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IrrigationPlanDetails {
    private Integer durationOfIrrigationInMinutes;
    private Integer intervalOfIrrigationInMinutes;
    private Integer amountOfWaterRequiredInLitres;
}
