package com.sc101.imaggregator.service.model;

import java.util.Arrays;

/**
 * @author Amit Tal
 * @since 5/6/2014
 */
public class ImMessage {

    private ImType imType;
    private Participant from;
    private Participant[] to;
    private String data;

    public ImMessage() {
    }

    public ImMessage(ImType imType, String data, Participant from, Participant... to) {
        this.imType = imType;
        this.data = data;
        this.from = from;
        this.to = to;
    }

    public ImType getImType() {
        return imType;
    }

    public void setImType(ImType imType) {
        this.imType = imType;
    }

    public Participant getFrom() {
        return from;
    }

    public void setFrom(Participant from) {
        this.from = from;
    }

    public Participant[] getTo() {
        return to;
    }

    public void setTo(Participant[] to) {
        this.to = to;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ImMessage{");
        sb.append("imType=").append(imType);
        sb.append(", from=").append(from);
        sb.append(", to=").append(Arrays.toString(to));
        sb.append(", data='").append(data).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
