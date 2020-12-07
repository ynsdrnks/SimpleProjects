package com.ynsdrnks.simplejpaonetoone.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ynsdrnks.simplejpaonetoone.entity.Country;
import com.ynsdrnks.simplejpaonetoone.entity.Districts;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;
@Data

public class CityDto {

    @JsonProperty
    private Integer cityId;

    @JsonProperty
    private String cityName;


}
