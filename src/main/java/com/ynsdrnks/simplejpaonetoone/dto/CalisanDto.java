package com.ynsdrnks.simplejpaonetoone.dto;

import com.ynsdrnks.simplejpaonetoone.entity.Adress;
import com.ynsdrnks.simplejpaonetoone.entity.MoreInfo;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
public class CalisanDto {

    private String clsnFirstName;

    private Long clsnId;

    private String clsnLastName;

    private String clsnEmail;

    private MoreInfo moreInfo;

    private List<Adress> adresses;
}
