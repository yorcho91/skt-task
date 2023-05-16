package com.encora.skt.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class Result {

    private boolean ok;

    private String message;

    private String detailedMessage;

}
