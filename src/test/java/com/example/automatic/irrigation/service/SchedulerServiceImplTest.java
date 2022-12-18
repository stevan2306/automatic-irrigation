package com.example.automatic.irrigation.service;

import com.example.automatic.irrigation.entity.IrrigationPlan;
import com.example.automatic.irrigation.entity.Plot;
import com.example.automatic.irrigation.entity.Sensor;
import com.example.automatic.irrigation.modal.IrrigationPlanDetails;
import com.example.automatic.irrigation.modal.PlotRequest;
import com.example.automatic.irrigation.modal.SensorDetails;
import com.example.automatic.irrigation.repository.PlotRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class SchedulerServiceImplTest {

    @Mock
    private PlotRepository plotRepository;

    @InjectMocks
    SchedulerServiceImpl schedulerService;

    @Test
    public void updateIrrigationTimeTest() {
        schedulerService.updateIrrigationTime(1);
    }

    @Before
    public void setup() {
        PlotRequest plotRequest = new PlotRequest();
        plotRequest.setName("Plot A");
        IrrigationPlanDetails irrigationPlanDetails = new IrrigationPlanDetails();
        irrigationPlanDetails.setIntervalOfIrrigationInMinutes(15);
        irrigationPlanDetails.setAmountOfWaterRequiredInLitres(100);
        irrigationPlanDetails.setDurationOfIrrigationInMinutes(5);
        SensorDetails sensorDetails = new SensorDetails();
        sensorDetails.setName("Sensor - Plot A");
        plotRequest.setIrrigationPlanDetails(irrigationPlanDetails);
        plotRequest.setSensorDetails(sensorDetails);
        Plot plot = new Plot();
        plot.setId(1);
        plot.setName("Plot A");
        Sensor sensor = new Sensor();
        sensor.setName("sensor Test A");
        plot.setSensor(sensor);
        IrrigationPlan irrigationPlan = new IrrigationPlan();
        irrigationPlan.setAmountOfWaterRequiredInLitres(1000);
        irrigationPlan.setIntervalInMinutes(10);
        irrigationPlan.setIntervalInMinutes(15);
        plot.setIrrigationPlan(irrigationPlan);
        Mockito.when(plotRepository.save(Mockito.any())).thenReturn(plot);
        Optional<Plot> optionalPlot = Optional.of(plot);
        Mockito.when(plotRepository.findById(1)).thenReturn(optionalPlot);
    }
}
