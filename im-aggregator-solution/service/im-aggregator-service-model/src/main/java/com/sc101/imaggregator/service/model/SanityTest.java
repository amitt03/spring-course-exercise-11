package com.sc101.imaggregator.service.model;

import java.util.UUID;

/**
 * @author Amit Tal
 * @since 1/8/14
 */
public class SanityTest {

    private String id;
    private String requesterName;
    private String extraData;

    public SanityTest() {
        this(null, null);
    }

    public SanityTest(String requesterName, String extraData) {
        this(null, requesterName, extraData);
    }

    public SanityTest(String id, String requesterName, String extraData) {
        setId(id);
        this.requesterName = requesterName;
        this.extraData = extraData;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (id == null) {
            this.id = UUID.randomUUID().toString();
        } else {
            this.id = id;
        }
    }

    public String getRequesterName() {
        return requesterName;
    }

    public void setRequesterName(String requesterName) {
        this.requesterName = requesterName;
    }

    public String getExtraData() {
        return extraData;
    }

    public void setExtraData(String extraData) {
        this.extraData = extraData;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SanityTest{");
        sb.append("id='").append(id).append('\'');
        sb.append(", requesterName='").append(requesterName).append('\'');
        sb.append(", extraData='").append(extraData).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
