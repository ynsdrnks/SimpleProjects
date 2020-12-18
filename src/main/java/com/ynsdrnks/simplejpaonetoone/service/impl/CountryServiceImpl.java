package com.ynsdrnks.simplejpaonetoone.service.impl;

import com.ynsdrnks.simplejpaonetoone.entity.Country;
import com.ynsdrnks.simplejpaonetoone.repository.CountryRepository;
import com.ynsdrnks.simplejpaonetoone.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("countryService")

public class CountryServiceImpl implements CountryService {

    @Qualifier("countryRepository")
    @Autowired
    private CountryRepository countryRepository;

    @Override
    public Iterable<Country> findAllCountries() {
        return countryRepository.findAll();
    }


    @Override
    public Country findCountryById(int id) {
        return countryRepository.findById(id).get();
    }

    @Override
    public void save(Country country) {
        countryRepository.save(country);
    }

    @Override
    public void saveList(Iterable<Country> countries) {
        countryRepository.saveAll(countries);
    }
}
