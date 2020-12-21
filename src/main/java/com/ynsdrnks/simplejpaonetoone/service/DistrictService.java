package com.ynsdrnks.simplejpaonetoone.service;

import com.ynsdrnks.simplejpaonetoone.entity.City;
import com.ynsdrnks.simplejpaonetoone.entity.Country;
import com.ynsdrnks.simplejpaonetoone.entity.District;

import java.util.List;

public interface DistrictService {

    public List<District> findByCity(int id);
    void saveList(Iterable<District> districts);
    public List<District> findAll();
    public District findDistrictById(int id);
}
