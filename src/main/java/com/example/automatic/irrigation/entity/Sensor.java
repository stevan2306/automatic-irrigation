package com.example.automatic.irrigation.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "last_irrigated_time")
    private String lastIrrigatedTime;

    @OneToOne(mappedBy = "sensor")
    private Plot plot;

}
