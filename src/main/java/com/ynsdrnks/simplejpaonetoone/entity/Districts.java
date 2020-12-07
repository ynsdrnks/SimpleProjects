package com.ynsdrnks.simplejpaonetoone.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "districts")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Districts implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "district_id",unique = true,nullable = false)
    private Integer districtId;

    @Column(name = "name")
    private String districtName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cityId",nullable = false)
    private City city;

    public Districts(Integer id, String districtName) {
        this.districtId = id;
        this.districtName = districtName;
    }
}
