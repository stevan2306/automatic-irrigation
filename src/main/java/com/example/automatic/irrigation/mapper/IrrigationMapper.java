package com.example.automatic.irrigation.mapper;

import com.example.automatic.irrigation.entity.IrrigationPlan;
import com.example.automatic.irrigation.entity.Plot;
import com.example.automatic.irrigation.entity.Sensor;
import com.example.automatic.irrigation.modal.IrrigationPlanDetails;
import com.example.automatic.irrigation.modal.PlotRequest;
import com.example.automatic.irrigation.modal.PlotResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class IrrigationMapper {

    public Plot mapToPlot(PlotRequest plotRequest, Plot plot) {
        if (plot == null) {
            plot = new Plot();
        }
        plot.setName(plotRequest.getName());
        Sensor sensor = plot.getSensor() == null ? new Sensor() : plot.getSensor();
        sensor.setName(plotRequest.getSensorDetails().getName());
        plot.setSensor(sensor);
        IrrigationPlan plan = plot.getIrrigationPlan() == null ? new IrrigationPlan(): plot.getIrrigationPlan();
        IrrigationPlanDetails irrigationPlanDetails = plotRequest.getIrrigationPlanDetails();
        plan.setDurationInMinutes(irrigationPlanDetails.getDurationOfIrrigationInMinutes());
        plan.setIntervalInMinutes(irrigationPlanDetails.getIntervalOfIrrigationInMinutes());
        plan.setAmountOfWaterRequiredInLitres(irrigationPlanDetails.getAmountOfWaterRequiredInLitres());
        plot.setIrrigationPlan(plan);
        return plot;
    }

    public PlotResponse mapToPlotResponse(Plot plot) {
        PlotResponse plotResponse = new PlotResponse();
        plotResponse.setId(plot.getId());
        plotResponse.setName(plot.getName());
        IrrigationPlan irrigationPlan = plot.getIrrigationPlan();
        IrrigationPlanDetails irrigationPlanDetails = new IrrigationPlanDetails();
        irrigationPlanDetails.setDurationOfIrrigationInMinutes(irrigationPlan.getDurationInMinutes());
        irrigationPlanDetails.setIntervalOfIrrigationInMinutes(irrigationPlan.getIntervalInMinutes());
        irrigationPlanDetails.setAmountOfWaterRequiredInLitres(irrigationPlan.getAmountOfWaterRequiredInLitres());
        plotResponse.setIrrigationPlan(irrigationPlanDetails);
        plotResponse.setSensorName(plot.getSensor().getName());
        plotResponse.setLastIrrigatedTime(plot.getSensor().getLastIrrigatedTime());
        return plotResponse;
    }

    public List<PlotResponse> mapToPlotResponseList(List<Plot> plots) {
        return plots.stream().map(this::mapToPlotResponse).collect(Collectors.toList());
    }

}
