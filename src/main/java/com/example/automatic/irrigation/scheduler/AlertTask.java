package com.example.automatic.irrigation.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TimerTask;

public class AlertTask extends TimerTask {

    Logger logger = LoggerFactory.getLogger(AlertTask.class);

    private String plotName;

    public AlertTask(String plotName) {
        this.plotName = plotName;
    }

    @Override
    public void run() {
        logger.warn("Sensor not configured for plot {}",  this.plotName);
    }
}
