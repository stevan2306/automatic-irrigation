package com.example.automatic.irrigation.intergration;

import com.example.automatic.irrigation.entity.Plot;
import com.example.automatic.irrigation.repository.PlotRepository;
import com.example.automatic.irrigation.scheduler.AlertTask;
import com.example.automatic.irrigation.scheduler.IrrigationTask;
import com.example.automatic.irrigation.service.SchedulerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;

@Component
public class SensorIntegration {

    private Logger logger = LoggerFactory.getLogger(SensorIntegration.class);

    @Autowired
    private PlotRepository plotRepository;

    @Autowired
    private SchedulerService schedulerService;

    private Map<Integer, Timer> sensors = new HashMap<>();

    @Value("${sensor.monitor.interval.minutes}")
    private int sensorMonitorIntervalInMinutes;


    @PostConstruct
    public void init() {
        logger.info("Sensor started to monitor plot every {} minutes for irrigation", sensorMonitorIntervalInMinutes);
        List<Plot> plotList = this.plotRepository.findAll();
        for (Plot plot : plotList) {
            addSensorConfigure(plot);
        }

    }

    public void addSensorConfigure(Plot plot) {
        // alert if sensor not added to plot
        if (plot.getSensor() == null) {
            logger.warn("Sensor not added to plot: {}", plot.getName());
            Timer timer = new Timer();
            sensors.put(plot.getId(), null);
            timer.schedule(new AlertTask(plot.getName()), 0, sensorMonitorIntervalInMinutes * 60 * 1000L);
        } else {
            logger.info("sensor added to plot {}", plot.getName());
            Timer timer = new Timer();
            sensors.put(plot.getId(), timer);
            timer.schedule(new IrrigationTask(plot.getId(), this.schedulerService), 0, sensorMonitorIntervalInMinutes * 60 * 1000L);
        }

    }

    public void updateSensorConfig(Plot plot) {
        logger.info("sensor update to plot with Id {}", plot.getId());
        sensors.get(plot.getId()).cancel();
        sensors.remove(plot.getId());
        addSensorConfigure(plot);
    }

    public void removeSensorConfig(Integer plotId) {
        logger.info("sensor removed to plot with Id {}", plotId);
        sensors.get(plotId).cancel();
        sensors.remove(plotId);
    }
}
