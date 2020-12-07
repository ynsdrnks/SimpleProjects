package com.ynsdrnks.simplejpaonetoone.service;

import com.ynsdrnks.simplejpaonetoone.entity.City;

import java.util.List;

public interface CityService {
    public List<City> findByCountry(int id);
}
