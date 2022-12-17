package com.example.automatic.irrigation.service;

import com.example.automatic.irrigation.entity.IrrigationPlan;
import com.example.automatic.irrigation.entity.Plot;
import com.example.automatic.irrigation.entity.Sensor;
import com.example.automatic.irrigation.repository.PlotRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class SchedulerServiceImpl implements SchedulerService {

    private Logger logger = LoggerFactory.getLogger(SchedulerService.class);

    @Autowired
    private PlotRepository plotRepository;

    @Override
    public void updateIrrigationTime(Integer plotId) {
       Optional<Plot> optionalPlot = plotRepository.findById(plotId);
       if (optionalPlot.isPresent()) {
           Plot plot = optionalPlot.get();
           logger.info("scheduler configured for plot {}", plot.getName());
           IrrigationPlan irrigationPlan = plot.getIrrigationPlan();
           Sensor sensor = plot.getSensor();
           // update the irrigation time of sensor
           if (isIrrigationNeed(sensor.getLastIrrigatedTime(), irrigationPlan.getDurationInMinutes(), irrigationPlan.getIntervalInMinutes())) {
               sensor.setLastIrrigatedTime(LocalDateTime.now());
               logger.info("Plot {} is irrigated and updated the sensor", plot.getName());
           }
           plotRepository.save(plot);
       }

    }

    private boolean isIrrigationNeed(LocalDateTime lastIrrigation, Integer durationInMinutes, Integer IntervalIntMinutes) {
        if (lastIrrigation == null) {
            return true;
        }
        LocalDateTime nextIrrigationTime = lastIrrigation.plusMinutes(durationInMinutes + IntervalIntMinutes);
        return nextIrrigationTime.compareTo(LocalDateTime.now()) < 1;
    }
}
