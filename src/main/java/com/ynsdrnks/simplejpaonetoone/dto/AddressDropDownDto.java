package com.ynsdrnks.simplejpaonetoone.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ynsdrnks.simplejpaonetoone.entity.City;
import com.ynsdrnks.simplejpaonetoone.entity.Country;
import com.ynsdrnks.simplejpaonetoone.entity.District;
import lombok.Data;

@Data
public class AddressDropDownDto {

    @JsonProperty("calisan_id")
    private Long calisanId;

    @JsonProperty("adress_details")
    private String adressDetails;

    @JsonProperty
    private CountryDto country;

    @JsonProperty
    private CityDto city;

    @JsonProperty
    private DistrictsDto district;

}
