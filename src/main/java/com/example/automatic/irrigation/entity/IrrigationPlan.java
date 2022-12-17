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
    private String id;

    @Column
    private Integer duration;

    @Column
    private Integer interval;

    @OneToOne(mappedBy = "irrigationPlan")
    private Plot plot;


}
