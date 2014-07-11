package com.sc101.imaggregator.facade.rest.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import org.dozer.Mapping;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Amit Tal
 * @since 1/8/14
 *
 * Sanity test DTO
 * Notice that request email must not be null and must uphold email structure
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "SanityTestDTO", description = "Sanity Test Information")
public class SanityTestDTO implements Serializable {

    private static final long serialVersionUID = 8716443070786082516L;

    private String id;
    private String requesterEmail;
    private String extraData;

    public SanityTestDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @NotNull
    @Email
    @Mapping("requesterName")
    @ApiModelProperty(required = true)
    public String getRequesterEmail() {
        return requesterEmail;
    }

    public void setRequesterEmail(String requesterEmail) {
        this.requesterEmail = requesterEmail;
    }

    public String getExtraData() {
        return extraData;
    }

    public void setExtraData(String extraData) {
        this.extraData = extraData;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SanityTestDTO{");
        sb.append("id='").append(id).append('\'');
        sb.append(", requesterEmail='").append(requesterEmail).append('\'');
        sb.append(", extraData='").append(extraData).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
