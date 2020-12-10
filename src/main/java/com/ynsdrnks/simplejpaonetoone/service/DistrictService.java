package com.ynsdrnks.simplejpaonetoone.service;

import com.ynsdrnks.simplejpaonetoone.entity.District;

import java.util.List;

public interface DistrictService {

    public List<District> findByCity(int id);

}
