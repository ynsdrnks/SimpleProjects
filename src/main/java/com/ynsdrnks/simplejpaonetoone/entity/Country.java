package com.ynsdrnks.simplejpaonetoone.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "countriess")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Country implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id",unique = true,nullable = false)
    private Integer countryId;

    @Column(name = "name")
    private String countryName;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "country")
    private Set<City> cities;


}
