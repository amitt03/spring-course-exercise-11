package com.sc101.imaggregator.test.system.dto;

import java.io.Serializable;

/**
 * @author Amit Tal
 * @since 5/6/2014
 */
public class ImMessageDTO implements Serializable {

    private String userId;
    private String fromNumber;
    private String toNumber;
    private String data;
    private int bidInCent;

    public ImMessageDTO() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFromNumber() {
        return fromNumber;
    }

    public void setFromNumber(String fromNumber) {
        this.fromNumber = fromNumber;
    }

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
