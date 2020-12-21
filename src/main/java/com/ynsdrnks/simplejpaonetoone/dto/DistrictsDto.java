package com.ynsdrnks.simplejpaonetoone.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ynsdrnks.simplejpaonetoone.entity.City;
import lombok.Data;

@Data

public class DistrictsDto {

    @JsonProperty
    private Integer district_id;

    @JsonProperty
    private String district_name;
    



}
