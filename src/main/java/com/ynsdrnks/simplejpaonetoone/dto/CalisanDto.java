package com.ynsdrnks.simplejpaonetoone.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ynsdrnks.simplejpaonetoone.entity.AddressDropDown;
import com.ynsdrnks.simplejpaonetoone.entity.Adress;
import com.ynsdrnks.simplejpaonetoone.entity.MoreInfo;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
public class CalisanDto {

    @JsonProperty
    private String clsn_firstName;

    @JsonProperty
    private String clsn_lastName;

    @JsonProperty
    private String clsn_email;

    @JsonProperty
    private Long clsn_id;

}
