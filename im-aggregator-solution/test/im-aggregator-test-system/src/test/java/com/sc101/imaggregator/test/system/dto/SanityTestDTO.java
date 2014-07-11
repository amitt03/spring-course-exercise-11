package com.sc101.imaggregator.test.system.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;

/**
 * @author Amit Tal
 * @since 1/8/14
 *
 * Sanity Test DTO
 * Notice that the DTO field names are a bit different then the real SanityTestDTO in the facade.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SanityTestDTO implements Serializable {

    private String id;
    private String email;
    private String data;

    public SanityTestDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("requesterEmail")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty("extraData")
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SanityTestDTO that = (SanityTestDTO) o;

        if (data != null ? !data.equals(that.data) : that.data != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (data != null ? data.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SanityTestDTO{");
        sb.append("id='").append(id).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", data='").append(data).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
