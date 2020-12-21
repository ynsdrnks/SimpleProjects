package com.ynsdrnks.simplejpaonetoone.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ynsdrnks.simplejpaonetoone.entity.City;
import com.ynsdrnks.simplejpaonetoone.entity.Country;
import com.ynsdrnks.simplejpaonetoone.entity.District;
import lombok.Data;

@Data
public class AddressDropDownDto {

    @JsonProperty
    private Long calisan_id;

    @JsonProperty
    private String address_details;

    @JsonProperty
    private CountryDto country;

    @JsonProperty
    private CityDto city;

    @JsonProperty
    private DistrictsDto district;

}
