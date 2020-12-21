package com.ynsdrnks.simplejpaonetoone.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ynsdrnks.simplejpaonetoone.entity.Country;
import lombok.Data;

@Data

public class CityDto {

    @JsonProperty
    private Integer city_id;

    @JsonProperty
    private String city_name;


}
