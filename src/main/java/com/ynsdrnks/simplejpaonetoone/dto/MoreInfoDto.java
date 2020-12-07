package com.ynsdrnks.simplejpaonetoone.dto;

import lombok.Data;

import javax.persistence.Column;

@Data

public class MoreInfoDto {

    private Long moreInfoId;

    private String motName;

    private String fatName;

    private int numSibl;

}
