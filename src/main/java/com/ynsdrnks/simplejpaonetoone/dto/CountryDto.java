package com.ynsdrnks.simplejpaonetoone.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ynsdrnks.simplejpaonetoone.entity.City;
import lombok.Data;

import java.util.Set;

@Data

public class CountryDto {
    @JsonProperty
    private Integer countryId;
    @JsonProperty
    private String countryName;


}
