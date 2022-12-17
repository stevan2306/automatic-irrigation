package com.example.automatic.irrigation.service;

import com.example.automatic.irrigation.entity.Plot;
import com.example.automatic.irrigation.exception.IrrigationException;
import com.example.automatic.irrigation.mapper.IrrigationMapper;
import com.example.automatic.irrigation.modal.ErrorStatus;
import com.example.automatic.irrigation.modal.PlotRequest;
import com.example.automatic.irrigation.modal.PlotResponse;
import com.example.automatic.irrigation.repository.PlotRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlotServiceImpl implements PlotService {

    private Logger logger = LoggerFactory.getLogger(PlotService.class);

    @Autowired
    private PlotRepository plotRepository;

    @Autowired
    private IrrigationMapper irrigationMapper;

    @Override
    public PlotResponse addPlot( PlotRequest plotRequest) {
        try {
            Plot plot = irrigationMapper.mapToPlot(plotRequest, null);
            Plot savedPlot = plotRepository.save(plot);
            logger.info("created plot with ID: {}", savedPlot.getId());
            return irrigationMapper.mapToPlotResponse(savedPlot);
        } catch (Exception e) {
            logger.error("Exception occurs when addPlot", e);
            throw new IrrigationException(ErrorStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public PlotResponse updatePlot(Integer id, PlotRequest plotRequest) {
        try {
            Optional<Plot> plot =  plotRepository.findById(id);
            if (plot.isPresent()) {
                Plot updatedPlot = irrigationMapper.mapToPlot(plotRequest, plot.get());
                plotRepository.save(updatedPlot);
                logger.info("updated plot with ID: {}", id);
                return getPlotById(id);
            } else {
                logger.error("Update source not found for Id: {}", id);
                throw new IrrigationException(ErrorStatus.NOT_FOUND, "Update Source Not available");
            }
        } catch (IrrigationException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Exception occurs when updatePlot", e);
            throw new IrrigationException(ErrorStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public List<PlotResponse> getPlots() {
        try {
            List<Plot> plots = plotRepository.findAll();
            return irrigationMapper.mapToPlotResponseList(plots);
        } catch (Exception e) {
            logger.error("Exception occurs when getPlots", e);
            throw new IrrigationException(ErrorStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public PlotResponse getPlotById(Integer id) {
        try {
            Optional<Plot> plot = plotRepository.findById(id);
            if (plot.isPresent()) {
                return irrigationMapper.mapToPlotResponse(plot.get());
            } else {
                logger.info("Plot not found for given Id: {}", id);
                throw new IrrigationException(ErrorStatus.NOT_FOUND, "Plot not found");
            }
        } catch (Exception e) {
            logger.error("Exception occurs when getPlotById", e);
            throw new IrrigationException(e);
        }
    }

    @Override
    public String deletePlotById(Integer id) {
        try {
            plotRepository.deleteById(id);
            return "DELETED";
        } catch (Exception e) {
            logger.error("Exception occurs when deletePlotById", e);
            return e.getMessage();
        }
    }
}
