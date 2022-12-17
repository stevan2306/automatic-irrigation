package com.example.automatic.irrigation.controller;

import com.example.automatic.irrigation.modal.PlotRequest;
import com.example.automatic.irrigation.modal.PlotResponse;
import com.example.automatic.irrigation.service.PlotService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class PlotController extends BaseRestController {

    private Logger logger = LoggerFactory.getLogger(PlotController.class);

    private static final String BASE_URI = "/plot";

    @Autowired
    private PlotService plotService;

    @PostMapping(value = BASE_URI)
    public PlotResponse createPlot(@RequestBody PlotRequest plotRequest) {
        logger.info("started creating plot");
        return plotService.addPlot(plotRequest);
    }

    @PutMapping(value = BASE_URI + "/{id}")
    public PlotResponse updatePlot(@PathVariable(name = "id") Integer id, @RequestBody PlotRequest plotRequest) {
        logger.info("started updatePlot plot with Id: {}", id);
        return plotService.updatePlot(id, plotRequest);
    }

    @GetMapping(value = BASE_URI)
    public List<PlotResponse> getAllPlots() {
        logger.info("started getAllPlots");
        return plotService.getPlots();
    }

    @GetMapping(value = BASE_URI + "/{id}")
    public PlotResponse getPlotById(@PathVariable("id") Integer id) {
        logger.info("started getPlotById plot with Id: {}", id);
        return plotService.getPlotById(id);
    }

    @DeleteMapping(value = BASE_URI + "/{id}")
    public String deletePlotById(@PathVariable("id") Integer id) {
        logger.info("started deletePlotById plot with Id: {}", id);
        return plotService.deletePlotById(id);
    }

}
