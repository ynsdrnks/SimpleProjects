package com.ynsdrnks.simplejpaonetoone.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;


@Setter
@Getter
@Entity
@ToString
@Table(name="moreinfo")
public class MoreInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "moreinfo_id")
    private Long moreInfoId;

    @Column(name = "motname")
    private String motName;

    @Column(name = "fatname")
    private String fatName;

    @Column(name = "numsibl")
    private int numSibl;

}

