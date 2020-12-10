package com.ynsdrnks.simplejpaonetoone.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "address_dropdown")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDropDown implements Serializable {


    @Id
    @GeneratedValue
    @Column(name = "adress_dropdown_id")
    private Long adressDropDownId;

    @Column
    private Long calisanId;

    @Column
    private String addressDetails;


    @ManyToOne()
    @JoinColumn(name = "country_id",referencedColumnName = "country_id",foreignKey = @ForeignKey(name = "none"))
    private Country country;

    @ManyToOne()
    @JoinColumn(name = "city_id",referencedColumnName = "city_id",foreignKey = @ForeignKey(name = "none"))
    private City city;

    @ManyToOne()
    @JoinColumn(name = "district_id",referencedColumnName = "district_id",foreignKey = @ForeignKey(name = "none"))
    private District district;

}
