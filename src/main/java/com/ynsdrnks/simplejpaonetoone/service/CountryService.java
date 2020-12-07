package com.ynsdrnks.simplejpaonetoone.service;

import com.ynsdrnks.simplejpaonetoone.entity.Country;

import java.util.List;

public interface CountryService {
    public Iterable<Country> findAllCountries();
    public Country findCountryById(int id);

}
