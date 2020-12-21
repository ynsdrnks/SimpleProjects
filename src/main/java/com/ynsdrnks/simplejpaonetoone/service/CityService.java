package com.ynsdrnks.simplejpaonetoone.service;

import com.ynsdrnks.simplejpaonetoone.entity.City;
import com.ynsdrnks.simplejpaonetoone.entity.Country;

import java.util.List;

public interface CityService {
    public List<City> findByCountry(int id);
    void saveList(Iterable<City> cities);
    public List<City> findAlll();
    public City findCityById(int id);
}
