package com.example.automatic.irrigation.service;

import com.example.automatic.irrigation.modal.PlotRequest;
import com.example.automatic.irrigation.modal.PlotResponse;
import org.springframework.stereotype.Service;

import java.util.List;


public interface PlotService {

    PlotResponse addPlot(PlotRequest plotRequest);

    PlotResponse updatePlot(Integer id, PlotRequest plotRequest);

    List<PlotResponse> getPlots();

    PlotResponse getPlotById(Integer id);

    String deletePlotById(Integer id);

}
