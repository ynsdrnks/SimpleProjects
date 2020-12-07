package com.ynsdrnks.simplejpaonetoone.dto;

import lombok.Data;

@Data
public class AdressDto {

    private Long adressId;

    private String sehir;

    private String ilce;

    private String mahalle;

    private String sokak;

    private int binaNum;

}
