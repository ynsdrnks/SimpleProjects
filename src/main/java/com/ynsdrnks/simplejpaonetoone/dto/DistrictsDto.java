package com.ynsdrnks.simplejpaonetoone.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ynsdrnks.simplejpaonetoone.entity.City;
import lombok.Data;

@Data

public class DistrictsDto {

    @JsonProperty("district_id")
    private Integer districtId;

    @JsonProperty("district_name")
    private String districtName;
    



}
