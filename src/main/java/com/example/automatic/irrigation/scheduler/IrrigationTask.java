package com.example.automatic.irrigation.scheduler;

import com.example.automatic.irrigation.service.SchedulerService;

import java.util.TimerTask;

public class IrrigationTask extends TimerTask {

    private Integer plotId;

    private SchedulerService schedulerService;

    public IrrigationTask(Integer plotId, SchedulerService schedulerService) {
        this.plotId = plotId;
        this.schedulerService = schedulerService;
    }

    @Override
    public void run() {
        this.schedulerService.updateIrrigationTime(plotId);
    }
}
