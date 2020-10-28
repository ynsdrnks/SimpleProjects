package com.ynsdrnks.simplejpaonetoone.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Table(name = "adress")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@ToString
public class Adress{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "adress_id")
    private Long adressId;
    @Column
    private Long calisanId;
    @Column(name = "sehir")
    private String sehir;

    @Column(name = "ilce")
    private String ilce;

    @Column(name = "mahalle")
    private String mahalle;

    @Column(name = "sokak")
    private String sokak;

    @Column(name = "bina_no")
    private int binaNum;


}
