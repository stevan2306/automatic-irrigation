package com.example.automatic.irrigation.service;

import com.example.automatic.irrigation.entity.Plot;
import com.example.automatic.irrigation.exception.IrrigationException;
import com.example.automatic.irrigation.mapper.IrrigationMapper;
import com.example.automatic.irrigation.modal.ErrorStatus;
import com.example.automatic.irrigation.modal.PlotRequest;
import com.example.automatic.irrigation.modal.PlotResponse;
import com.example.automatic.irrigation.repository.PlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlotServiceImpl implements PlotService{


    @Autowired
    private PlotRepository plotRepository;

    @Autowired
    private IrrigationMapper irrigationMapper;

    @Override
    public PlotResponse addPlot( PlotRequest plotRequest) {
        Plot plot = irrigationMapper.mapToPlot(plotRequest, null);
        Plot savedPlot = plotRepository.save(plot);
        return irrigationMapper.mapToPlotResponse(savedPlot);
    }

    @Override
    public PlotResponse updatePlot(String id, PlotRequest plotRequest) {
        try {
            Optional<Plot> plot =  plotRepository.findById(id);
            if (plot.isPresent()) {
                Plot updatedPlot = irrigationMapper.mapToPlot(plotRequest, plot.get());
                plotRepository.save(updatedPlot);
                return getPlotById(id);
            }
            return null;
        } catch (Exception e) {
           throw new IrrigationException(e);
        }
    }

    @Override
    public List<PlotResponse> getPlots() {
        List<Plot> plots = plotRepository.findAll();
        return irrigationMapper.mapToPlotResponseList(plots);
    }

    @Override
    public PlotResponse getPlotById(String id) {
        try {
            Optional<Plot> plot = plotRepository.findById(id);
            if (plot.isPresent()) {
                return irrigationMapper.mapToPlotResponse(plot.get());
            } else {
                throw new IrrigationException(ErrorStatus.NOT_FOUND, "Plot not found");
            }
        } catch (Exception e) {
            throw new IrrigationException(e);
        }
    }

    @Override
    public String deletePlotById(String id) {
        try {
            plotRepository.deleteById(id);
            return "DELETED";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
