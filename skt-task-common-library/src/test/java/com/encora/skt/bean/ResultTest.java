package com.encora.skt.bean;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultTest {

    private final Result result = new Result();

    @Test
    public void ok () {
        assertThat(result.setOk(true).isOk())
            .isEqualTo(true);
    }

    @Test
    public void message () {
        assertThat(result.setMessage("message").getMessage())
            .isEqualTo("message");
    }

    @Test
    public void detailedMessage () {
        assertThat(result.setDetailedMessage("detailedMessage").getDetailedMessage())
            .isEqualTo("detailedMessage");
    }
}
