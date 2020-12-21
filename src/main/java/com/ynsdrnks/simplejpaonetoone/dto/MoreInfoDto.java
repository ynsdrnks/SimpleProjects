package com.ynsdrnks.simplejpaonetoone.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Column;

@Data

public class MoreInfoDto {
    @JsonProperty
    private Long moreinfo_id;
    @JsonProperty
    private String mot_name;
    @JsonProperty
    private String fat_name;
    @JsonProperty
    private int num_sibl;

}
