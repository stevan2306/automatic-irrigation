package com.example.automatic.irrigation.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column
    private String name;

    @Column(name = "last_irrigated_time")
    private LocalDateTime lastIrrigatedTime;

    @OneToOne(mappedBy = "sensor")
    private Plot plot;

}
