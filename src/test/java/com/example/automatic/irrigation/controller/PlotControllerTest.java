package com.example.automatic.irrigation.controller;


import com.example.automatic.irrigation.exception.IrrigationException;
import com.example.automatic.irrigation.modal.ErrorStatus;
import com.example.automatic.irrigation.modal.PlotRequest;
import com.example.automatic.irrigation.modal.PlotResponse;
import com.example.automatic.irrigation.service.PlotService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@RunWith(MockitoJUnitRunner.class)
public class PlotControllerTest {

    @Mock
    private PlotService plotService;

    @InjectMocks
    PlotController plotController;

    @Test
    public void addPlotTest() {
        PlotRequest plotRequest = Mockito.mock(PlotRequest.class);
        PlotResponse plotResponse = Mockito.mock(PlotResponse.class);
        Mockito.when(plotService.addPlot(plotRequest)).thenReturn(plotResponse);
        PlotResponse response = plotController.createPlot(plotRequest);
        assertNotNull(response);

    }

    @Test
    public void getAllPlotsTest() {
        Mockito.when(plotService.getPlots()).thenReturn(new ArrayList<>());
        List<PlotResponse> response = plotController.getAllPlots();
        assertNotNull(response);

    }

    @Test
    public void getPlotByIdTest() {
        PlotResponse plotResponse = Mockito.mock(PlotResponse.class);
        Mockito.when(plotService.getPlotById(1)).thenReturn(plotResponse);
        PlotResponse response = plotController.getPlotById(1);
        assertNotNull(response);

    }

    @Test
    public void deletePlotByIdTest() {
        Mockito.when(plotService.deletePlotById(1)).thenReturn("DELETED");
        String response = plotController.deletePlotById(1);
        assertNotNull(response);

    }

    @Test
    public void updatePlotTest () {
        PlotRequest plotRequest = Mockito.mock(PlotRequest.class);
        PlotResponse plotResponse = Mockito.mock(PlotResponse.class);
        Mockito.when(plotService.updatePlot(1, plotRequest)).thenReturn(plotResponse);
        PlotResponse response = plotController.updatePlot(1, plotRequest);
        assertNotNull(response);

    }

    @Test(expected = IrrigationException.class )
    public void addPlotExceptionTest() {
        PlotRequest plotRequest = Mockito.mock(PlotRequest.class);
        Mockito.when(plotService.addPlot(plotRequest)).thenThrow(new IrrigationException(ErrorStatus.INTERNAL_SERVER_ERROR, "ExceptionTest"));
        plotController.createPlot(plotRequest);
    }

    @Test(expected = IrrigationException.class )
    public void getAllPlotsExceptionTest() {
        Mockito.when(plotService.getPlots()).thenThrow(new IrrigationException(ErrorStatus.INTERNAL_SERVER_ERROR, "ExceptionTest"));
        List<PlotResponse> response = plotController.getAllPlots();
        assertNotNull(response);

    }

    @Test(expected = IrrigationException.class )
    public void getPlotByIdExceptionTest() {
        Mockito.when(plotService.getPlotById(1)).thenThrow(new IrrigationException(ErrorStatus.INTERNAL_SERVER_ERROR, "ExceptionTest"));
        PlotResponse response = plotController.getPlotById(1);
        assertNotNull(response);

    }

    @Test(expected = IrrigationException.class )
    public void deletePlotByIdExceptionTest() {
        Mockito.when(plotService.deletePlotById(1)).thenThrow(new IrrigationException(ErrorStatus.INTERNAL_SERVER_ERROR, "ExceptionTest"));
        String response = plotController.deletePlotById(1);
        assertNotNull(response);

    }

    @Test(expected = IrrigationException.class )
    public void updatePlotExceptionTest () {
        PlotRequest plotRequest = Mockito.mock(PlotRequest.class);
        Mockito.when(plotService.updatePlot(1, plotRequest)).thenThrow(new IrrigationException(ErrorStatus.INTERNAL_SERVER_ERROR, "ExceptionTest"));
        PlotResponse response = plotController.updatePlot(1, plotRequest);
        assertNotNull(response);

    }

}
