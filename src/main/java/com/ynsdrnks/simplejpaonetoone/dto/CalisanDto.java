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

    @JsonProperty("clsn_first_name")
    private String clsnFirstName;

    @JsonProperty("clsn_last_name")
    private String clsnLastName;

    @JsonProperty("clsn_email")
    private String clsnEmail;

    @JsonProperty("clsn_id")
    private Long clsnId;

}
