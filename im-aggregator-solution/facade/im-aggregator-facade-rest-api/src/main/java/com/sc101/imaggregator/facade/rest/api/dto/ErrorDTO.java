package com.sc101.imaggregator.facade.rest.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;

/**
 * @author Amit Tal
 * @since 1/8/14
 *
 * This DTO is used by exception handlers to return an Error structure due to some failure.
 *
 * Notice:
 * This DTO structure is just an example and needs to be changed in order to supply your project needs.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorDTO implements Serializable {

    private static final long serialVersionUID = -1177499752240856849L;

    // TODO Change error DTO to fit your project concerns
    // TODO Change error DTO to fit your project concerns
    // TODO Change error DTO to fit your project concerns

    private String code;
    private String debugMessage;

    public ErrorDTO() {
    }

    public ErrorDTO(String code, String debugMessage) {
        this.code = code;
        this.debugMessage = debugMessage;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDebugMessage() {
        return debugMessage;
    }

    public void setDebugMessage(String debugMessage) {
        this.debugMessage = debugMessage;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ErrorDTO{");
        sb.append("code='").append(code).append('\'');
        sb.append(", debugMessage='").append(debugMessage).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
