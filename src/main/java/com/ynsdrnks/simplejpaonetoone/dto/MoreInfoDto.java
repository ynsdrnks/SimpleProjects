package com.ynsdrnks.simplejpaonetoone.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Column;

@Data

public class MoreInfoDto {
    @JsonProperty
    private Long moreInfoId;
    @JsonProperty
    private String motName;
    @JsonProperty
    private String fatName;
    @JsonProperty
    private int numSibl;

}
