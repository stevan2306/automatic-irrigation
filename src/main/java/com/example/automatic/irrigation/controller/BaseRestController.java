package com.example.automatic.irrigation.controller;

import com.example.automatic.irrigation.exception.IrrigationException;
import com.example.automatic.irrigation.modal.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.HashMap;
import java.util.Map;

@RequestMapping(value = "/api/v1")
public class BaseRestController {

    @ExceptionHandler(value = IrrigationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView handleException(IrrigationException exception) {
        MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
        Map<String, String> map = new HashMap<>();
        map.put("status", exception.getErrorStatus().name());
        map.put("description", exception.getDescription());
        return new ModelAndView(jsonView, map);
    }
}
