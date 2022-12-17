package com.example.automatic.irrigation.intergration;

import com.example.automatic.irrigation.entity.Plot;
import com.example.automatic.irrigation.repository.PlotRepository;
import com.example.automatic.irrigation.scheduler.IrrigationTask;
import com.example.automatic.irrigation.service.SchedulerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Timer;

@Component
public class SensorIntegration {

    private Logger logger = LoggerFactory.getLogger(SensorIntegration.class);

    @Autowired
    private PlotRepository plotRepository;

    @Autowired
    private SchedulerService schedulerService;


    private Long delayInMinutes = 5 * 60 * 1000L;

    @PostConstruct
    public void init() {
        logger.info("Sensor started to monitor plot every 5 mins for irrigation");
        List<Plot> plotList = this.plotRepository.findAll();
        for (Plot plot : plotList) {
            Timer timer = new Timer();
            timer.schedule(new IrrigationTask(plot.getId(), this.schedulerService), delayInMinutes);
        }

    }

}
