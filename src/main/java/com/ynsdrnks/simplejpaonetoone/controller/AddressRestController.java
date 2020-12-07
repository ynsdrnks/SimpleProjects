package com.ynsdrnks.simplejpaonetoone.controller;


import com.google.gson.Gson;
import com.ynsdrnks.simplejpaonetoone.converter.Converter;
import com.ynsdrnks.simplejpaonetoone.dto.CityDto;
import com.ynsdrnks.simplejpaonetoone.dto.CountryDto;
import com.ynsdrnks.simplejpaonetoone.dto.DistrictsDto;
import com.ynsdrnks.simplejpaonetoone.entity.Adress;
import com.ynsdrnks.simplejpaonetoone.entity.City;
import com.ynsdrnks.simplejpaonetoone.entity.Country;
import com.ynsdrnks.simplejpaonetoone.entity.Districts;
import com.ynsdrnks.simplejpaonetoone.service.CountryService;
import com.ynsdrnks.simplejpaonetoone.service.DistrictService;
import com.ynsdrnks.simplejpaonetoone.service.impl.CityServiceImpl;
import com.ynsdrnks.simplejpaonetoone.service.impl.CountryServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.GeneratorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
@RestController
public class AddressRestController {
    @Autowired
    private CountryServiceImpl countryService;

    @Autowired
    private CityServiceImpl cityService;

    @Autowired
    private DistrictService districtService;

    @Autowired
    private Converter converter;


    @GetMapping("/countryList")
    ResponseEntity<?> getCountry(){
        return new ResponseEntity<List<CountryDto>>(converter.countryListConvertToDtoList((List<Country>)countryService.findAllCountries()), HttpStatus.OK);
    }

    @GetMapping("/cityByCountry")
    ResponseEntity<?> getCities(@RequestParam("country_id")Integer countryId){
        return new ResponseEntity<List<CityDto>>(converter.cityListConvertToDtoList(cityService.findByCountry(countryId)), HttpStatus.OK);
    }


    @GetMapping("/districtsByCity")
    ResponseEntity<?> getDistricts(@RequestParam("city_id")Integer cityId){
        return new ResponseEntity<List<DistrictsDto>>(converter.districtsListConvertToDtoList((districtService.findByCity(cityId))), HttpStatus.OK);
    }

}
