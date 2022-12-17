package com.example.automatic.irrigation.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "irrigation_plan")
public class IrrigationPlan {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "duration_in_minutes")
    private Integer durationInMinutes;

    @Column(name = "interval_in_minutes")
    private Integer intervalInMinutes;

    @Column(name = "required_water_in_litres")
    private Integer amountOfWaterRequiredInLitres;

    @OneToOne(mappedBy = "irrigationPlan")
    private Plot plot;


}
