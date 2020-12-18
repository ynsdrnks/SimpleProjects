package com.ynsdrnks.simplejpaonetoone.service.impl;

import com.ynsdrnks.simplejpaonetoone.entity.City;
import com.ynsdrnks.simplejpaonetoone.entity.Country;
import com.ynsdrnks.simplejpaonetoone.repository.CityRepository;
import com.ynsdrnks.simplejpaonetoone.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CityServiceImpl implements CityService {


    @Qualifier("cityRepository")
    @Autowired
    private CityRepository cityRepository;

    @Override
    public List<City> findByCountry(int id) {
        return cityRepository.findByCountry(id);
    }

    @Override
    public void saveList(Iterable<City> cities) {
        cityRepository.saveAll(cities);
    }
}
