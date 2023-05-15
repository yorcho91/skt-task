package com.encora.skt.controller;

import org.junit.Test;
import static com.encora.skt.controller.ExceptionControllerAdvice.VIEW_NAME;
import static org.assertj.core.api.Assertions.assertThat;

public class ExceptionControllerAdviceTest {

    @Test
    public void exceptionHandler() {
        assertThat(
            new ExceptionControllerAdvice()
                .exceptionHandler(
                    new Exception("exceptionHandlerTest"))
        )
            .extracting("viewName")
            .containsExactly(VIEW_NAME);
    }

}
