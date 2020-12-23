package com.ynsdrnks.simplejpaonetoone.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Column;

@Data

public class MoreInfoDto {

    @JsonProperty("moreinfo_id")
    private Long moreinfoId;

    @JsonProperty("mot_name")
    private String motName;

    @JsonProperty("fat_name")
    private String fatName;

    @JsonProperty("num_sibl")
    private int numSibl;

}
