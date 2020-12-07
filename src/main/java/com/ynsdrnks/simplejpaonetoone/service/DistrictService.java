package com.ynsdrnks.simplejpaonetoone.service;

import com.ynsdrnks.simplejpaonetoone.entity.Districts;

import java.util.List;

public interface DistrictService {

    public List<Districts> findByCity(int id);

}
