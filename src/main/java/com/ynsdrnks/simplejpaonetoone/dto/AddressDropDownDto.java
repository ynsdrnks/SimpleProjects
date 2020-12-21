package com.ynsdrnks.simplejpaonetoone.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ynsdrnks.simplejpaonetoone.entity.City;
import com.ynsdrnks.simplejpaonetoone.entity.Country;
import com.ynsdrnks.simplejpaonetoone.entity.District;
import lombok.Data;

@Data
public class AddressDropDownDto {

    @JsonProperty
    private Long calisanId;

    @JsonProperty
    private String addressDetails;

    @JsonProperty
    private Country country;

    @JsonProperty
    private City city;

    @JsonProperty
    private District district;

}
