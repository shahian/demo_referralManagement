package com.haytech.demo_referralmanagement.model.base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.haytech.demo_referralmanagement.utility.ApplicationProperties;


@JsonIgnoreProperties(ignoreUnknown = true)

public class MetaDTO {
    private Integer code;
    private String message;
    private String httpStatus;

    public MetaDTO(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public MetaDTO(Integer code, String httpStatus, String message) {
        this.code = code;
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public MetaDTO() {
    }

    public static MetaDTO getInstance(ApplicationProperties applicationProperties) {
        return new MetaDTO(
                applicationProperties.getCode("application.message.success.code"),
                applicationProperties.getProperty("application.message.success.text")
        );
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(String httpStatus) {
        this.httpStatus = httpStatus;}
}

