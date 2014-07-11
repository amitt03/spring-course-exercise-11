package com.sc101.imaggregator.facade.rest.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @author Amit Tal
 * @since 5/6/2014
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "ImMessageDTO", description = "IM Message DTO")
public class ImMessageDTO implements Serializable {

    private String userId;
    private String fromNumber;
    private String toNumber;
    private String data;
    private int bidInCent;

    public ImMessageDTO() {
    }

    @NotNull
    @ApiModelProperty(required = true)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Pattern(regexp = "^\\d{10}", message = "Number must be exactly 10 digits")
    @ApiModelProperty(required = true)
    public String getFromNumber() {
        return fromNumber;
    }

    public void setFromNumber(String fromNumber) {
        this.fromNumber = fromNumber;
    }

    @Pattern(regexp = "^\\d{10}", message = "Number must be exactly 10 digits")
    @ApiModelProperty(required = true)
    public String getToNumber() {
        return toNumber;
    }

    public void setToNumber(String toNumber) {
        this.toNumber = toNumber;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Min(value = 0, message = "Bid must be larger then 0")
    @ApiModelProperty(required = true)
    public int getBidInCent() {
        return bidInCent;
    }

    public void setBidInCent(int bidInCent) {
        this.bidInCent = bidInCent;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ImMessageDTO{");
        sb.append("userId='").append(userId).append('\'');
        sb.append(", fromNumber='").append(fromNumber).append('\'');
        sb.append(", toNumber='").append(toNumber).append('\'');
        sb.append(", data='").append(data).append('\'');
        sb.append(", bidInCent=").append(bidInCent);
        sb.append('}');
        return sb.toString();
    }
}
