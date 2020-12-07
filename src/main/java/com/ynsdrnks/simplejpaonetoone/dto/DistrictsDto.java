package com.ynsdrnks.simplejpaonetoone.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ynsdrnks.simplejpaonetoone.entity.City;
import lombok.Data;

@Data

public class DistrictsDto {

    @JsonProperty
    private Integer districtId;

    @JsonProperty
    private String districtName;


}
