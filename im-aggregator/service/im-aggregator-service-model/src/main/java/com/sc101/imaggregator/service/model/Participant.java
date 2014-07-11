package com.sc101.imaggregator.service.model;

/**
 * @author Amit Tal
 * @since 5/6/2014
 */
public class Participant {

    private String displayName;
    private String number;

    public Participant() {
    }

    public Participant(String displayName, String number) {
        this.displayName = displayName;
        this.number = number;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Participant{");
        sb.append("displayName='").append(displayName).append('\'');
        sb.append(", number='").append(number).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
