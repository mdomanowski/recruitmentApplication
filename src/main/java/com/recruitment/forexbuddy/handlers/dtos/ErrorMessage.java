package com.recruitment.forexbuddy.handlers.dtos;

import lombok.Builder;

@Builder
public class ErrorMessage {
    private String errorDescription;

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }
}
