package com.example.automatic.irrigation.controller;


import com.example.automatic.irrigation.modal.PlotRequest;
import com.example.automatic.irrigation.modal.PlotResponse;
import com.example.automatic.irrigation.service.PlotService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

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

}
