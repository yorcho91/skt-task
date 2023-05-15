package com.encora.skt.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionControllerAdvice {

    static final String ATTRIBUTE_NAME_MESSAGE = "message";
    static final String VIEW_NAME = "exception";

    @ExceptionHandler(value = Exception.class)
    public ModelAndView exceptionHandler(Exception exception) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(ATTRIBUTE_NAME_MESSAGE, exception.getMessage());
        modelAndView.setViewName(VIEW_NAME);
        return modelAndView;
    }

}
