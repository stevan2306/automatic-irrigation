package com.example.automatic.irrigation.service;

import com.example.automatic.irrigation.entity.IrrigationPlan;
import com.example.automatic.irrigation.entity.Plot;
import com.example.automatic.irrigation.entity.Sensor;
import com.example.automatic.irrigation.exception.IrrigationException;
import com.example.automatic.irrigation.intergration.SensorIntegration;
import com.example.automatic.irrigation.mapper.IrrigationMapper;
import com.example.automatic.irrigation.modal.IrrigationPlanDetails;
import com.example.automatic.irrigation.modal.PlotRequest;
import com.example.automatic.irrigation.modal.PlotResponse;
import com.example.automatic.irrigation.modal.SensorDetails;
import com.example.automatic.irrigation.repository.PlotRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class PlotServiceImplTest {

    @Mock
    private PlotRepository plotRepository;

    @Spy
    private IrrigationMapper irrigationMapper;

    @Mock
    private SensorIntegration sensorIntegration;


    @InjectMocks
    private PlotServiceImpl plotService;

    private PlotRequest plotRequest;

    @Before
    public void setup() {
        plotRequest = new PlotRequest();
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
        Mockito.doNothing().when(sensorIntegration).addSensorConfigure(Mockito.any());
        Mockito.doNothing().when(plotRepository).deleteById(Mockito.anyInt());
        Mockito.when(plotRepository.findAll()).thenReturn(new ArrayList<>());
        Optional<Plot> optionalPlot = Optional.of(plot);
        Mockito.when(plotRepository.findById(1)).thenReturn(optionalPlot);
    }

    @Test
    public void addPlotTest() {
        PlotResponse plotResponse = plotService.addPlot(plotRequest);
        assertNotNull(plotResponse);
    }

    @Test
    public void updatePlotTest() {
        PlotResponse plotResponse = plotService.updatePlot(1, plotRequest);
        assertNotNull(plotResponse);
    }

    @Test
    public void getAllPlotTest() {
        List<PlotResponse> plotResponse = plotService.getPlots();
        assertNotNull(plotResponse);
    }

    @Test
    public void getAllPlotByIdTest() {
        PlotResponse plotResponse = plotService.getPlotById(1);
        assertNotNull(plotResponse);
    }

    @Test
    public void deletePlotTest() {
        String result = plotService.deletePlotById(1);
        assertNotNull(result);
    }

    @Test(expected = IrrigationException.class)
    public void addPlotExceptionTest() {
        Mockito.when(plotRepository.save(Mockito.any())).thenThrow(new RuntimeException());
        plotService.addPlot(plotRequest);
    }

    @Test(expected = IrrigationException.class)
    public void updatePlotExceptionTest() {
        Mockito.when(plotRepository.save(Mockito.any())).thenThrow(new RuntimeException());
        plotService.updatePlot(1, plotRequest);
    }

    @Test(expected = IrrigationException.class)
    public void getAllPlotExceptionTest() {
        Mockito.when(plotRepository.findAll()).thenThrow(new RuntimeException());
        plotService.getPlots();
    }

    @Test(expected = IrrigationException.class)
    public void getAllPlotByIdExceptionTest() {
        Mockito.when(plotRepository.findById(Mockito.any())).thenThrow(new RuntimeException());
        plotService.getPlotById(1);
    }

    @Test
    public void deletePlotExceptionTest() {
        Mockito.doThrow(new RuntimeException("could not be deleted")).when(plotRepository).deleteById(Mockito.any());
        String response = plotService.deletePlotById(1);
        assertNotNull(response);
    }

}
