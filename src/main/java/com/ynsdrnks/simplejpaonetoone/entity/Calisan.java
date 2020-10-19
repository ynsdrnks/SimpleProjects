package com.ynsdrnks.simplejpaonetoone.entity;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.*;


@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Table(name="calisan")
@Builder
public class Calisan{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="clsn_id")
    private Long id;

    @Column(name="clsn_fname")
    private String clsnFirstName;

    @Column(name = "clsn_lname")
    private String clsnLastName;

    @Column(name="clsn_email")
    private String clsnEmail;

    @OneToOne(cascade = {CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "moreInfo_id")
    private MoreInfo moreInfo;


}