package com.example.automatic.irrigation.controller;

import com.example.automatic.irrigation.exception.IrrigationException;
import com.example.automatic.irrigation.modal.PlotRequest;
import com.example.automatic.irrigation.modal.PlotResponse;
import com.example.automatic.irrigation.service.PlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plot")
public class PlotController {

    @Autowired
    private PlotService plotService;

    @PostMapping
    public PlotResponse createPlot(@RequestBody PlotRequest plotRequest) {
        try {
            return plotService.addPlot(plotRequest);
        } catch (Exception e) {
              throw new IrrigationException(e);
        }
    }

    @PutMapping("/{id}")
    public PlotResponse updatePlot(@PathVariable(name = "id") String id, @RequestBody PlotRequest plotRequest) {
        try {
            return plotService.updatePlot(id, plotRequest);
        } catch (Exception e) {
            throw new IrrigationException(e);
        }
    }

    @GetMapping
    public List<PlotResponse> getAllPlots() {
        try {
            return plotService.getPlots();
        } catch (Exception e) {
            throw new IrrigationException(e);
        }
    }

    @GetMapping("/{id}")
    public PlotResponse getPlotById(@PathVariable("id") String name) {
        try {
            return plotService.getPlotById(name);
        } catch (Exception e) {
            throw new IrrigationException(e);
        }
    }

    @GetMapping("/{id}")
    public String deletePlotById(@PathVariable("id") String id) {
        try {
            return plotService.deletePlotById(id);
        } catch (Exception e) {
            throw new IrrigationException(e);
        }
    }

}
