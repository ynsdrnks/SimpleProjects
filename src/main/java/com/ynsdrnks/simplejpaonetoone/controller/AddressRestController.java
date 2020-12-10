package com.ynsdrnks.simplejpaonetoone.controller;


import com.ynsdrnks.simplejpaonetoone.converter.Converter;
import com.ynsdrnks.simplejpaonetoone.dto.AdressDto;
import com.ynsdrnks.simplejpaonetoone.dto.CityDto;
import com.ynsdrnks.simplejpaonetoone.dto.CountryDto;
import com.ynsdrnks.simplejpaonetoone.dto.DistrictsDto;
import com.ynsdrnks.simplejpaonetoone.entity.Adress;
import com.ynsdrnks.simplejpaonetoone.entity.Country;
import com.ynsdrnks.simplejpaonetoone.service.DistrictService;
import com.ynsdrnks.simplejpaonetoone.service.impl.CalisanServiceImpl;
import com.ynsdrnks.simplejpaonetoone.service.impl.CityServiceImpl;
import com.ynsdrnks.simplejpaonetoone.service.impl.CountryServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @Autowired
    private CalisanServiceImpl calisanService;

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


    @GetMapping("/allAddress")
    ResponseEntity<?> getAllAddress(@RequestParam("calisan_id")Long calisanId){
        List<Adress> adressList = calisanService.getByCalisanId(calisanId).getAdresses();
        return new ResponseEntity<List<AdressDto>>(converter.adressListConvertToDtoList(adressList),HttpStatus.OK);
    }

}
