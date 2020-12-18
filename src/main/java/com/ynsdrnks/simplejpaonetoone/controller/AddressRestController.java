package com.ynsdrnks.simplejpaonetoone.controller;


import com.ynsdrnks.simplejpaonetoone.converter.Converter;
import com.ynsdrnks.simplejpaonetoone.dto.*;
import com.ynsdrnks.simplejpaonetoone.entity.*;
import com.ynsdrnks.simplejpaonetoone.exception.ResourceNotFoundException;
import com.ynsdrnks.simplejpaonetoone.repository.AddressDropDownRepository;
import com.ynsdrnks.simplejpaonetoone.repository.CityRepository;
import com.ynsdrnks.simplejpaonetoone.repository.CountryRepository;
import com.ynsdrnks.simplejpaonetoone.service.DistrictService;
import com.ynsdrnks.simplejpaonetoone.service.impl.AddressDropDownImpl;
import com.ynsdrnks.simplejpaonetoone.service.impl.CalisanServiceImpl;
import com.ynsdrnks.simplejpaonetoone.service.impl.CityServiceImpl;
import com.ynsdrnks.simplejpaonetoone.service.impl.CountryServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/addressApi")
public class AddressRestController {
    @Autowired
    private CountryServiceImpl countryService;

    @Autowired
    private CityServiceImpl cityService;

    @Qualifier("cityRepository")
    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private DistrictService districtService;

    @Autowired
    private Converter converter;

    @Autowired
    private CalisanServiceImpl calisanService;

    @Autowired
    private AddressDropDownRepository addressDropDownRepo;

    @Autowired
    private AddressDropDownImpl addressService;

    @GetMapping("/countryList")
    ResponseEntity<?> getCountry(){
        return new ResponseEntity<List<CountryDto>>(converter.countryListConvertToDtoList((List<Country>)countryService.findAllCountries()), HttpStatus.OK);
    }
    @GetMapping("/allCities")
    ResponseEntity<?> getAllCities(){
        return new ResponseEntity<List<CityDto>>(converter.cityListConvertToDtoList((List<City>)cityRepository.findAll()),HttpStatus.OK);
    }

    @GetMapping("/cityByCountry")
    ResponseEntity<?> getCities(@RequestParam("country_id")Integer  countryId){
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

    @GetMapping("/allAddress/{clsnId}")
    ResponseEntity<?> getAllAddressCalisan(@PathVariable("clsnId") Long calisanId){
        List<AddressDropDown> adressList = calisanService.getByCalisanId(calisanId).getAddressDropDowns();
        return new ResponseEntity<List<AddressDropDownDto>>(converter.adressDropdownListConvertToDtoList(adressList),HttpStatus.OK);
    }
    @GetMapping("/allAddress/{clsnId}/{addressId}")
    public  AddressDropDownDto getOneAddress(@PathVariable("clsnId")Long calisanId,@PathVariable("addressId")Long addressId){
        return converter.adressConvertToDto(addressService.getAdressById(addressId));
    }


    @PutMapping("/allAddress/{clsnId}/{addressId}")
    public  AddressDropDownDto updateAddress(@PathVariable("clsnId") Long clsnId,@PathVariable("addressId")Long addressId,
                                             @Valid @RequestBody AddressDropDown addressDropDownReq){
        return addressDropDownRepo.findById(addressId)
                .map(addressDropDown -> {
                    addressDropDown.setAddressDetails(addressDropDownReq.getAddressDetails());
                    addressDropDown.setCity(addressDropDownReq.getCity());
                    addressDropDown.setCountry(addressDropDownReq.getCountry());
                    addressDropDown.setDistrict(addressDropDownReq.getDistrict());
                    addressDropDown.setCalisanId(clsnId);
                    addressService.saveAdress(addressDropDown);
                    return converter.adressConvertToDto(addressDropDown);
                }).orElseThrow(() -> new ResourceNotFoundException("not found : "+addressId));
    }

    @PostMapping("/allAddress/{clsnId}")
    public  AddressDropDownDto createAddress(@PathVariable("clsnId")Long calisanId,@Valid @RequestBody AddressDropDown addressDropDown){
        addressDropDown.setCalisanId(calisanId);
        calisanService.getByCalisanId(calisanId).getAddressDropDowns().add(addressDropDown);
        addressService.saveAdress(addressDropDown);
        return converter.adressConvertToDto(addressDropDown);
    }

    @DeleteMapping("/allAddress/{clsnId}/{addressId}")
    public ResponseEntity<?> deleteAddress(@PathVariable("clsnId") long clsnId,@PathVariable("addressId") long addressId){
        return addressDropDownRepo.findById(addressId).map(addressDropDown -> {
            addressService.deleteAdressById(addressId);
            return ResponseEntity.ok().build();
        }).orElseThrow(()-> new ResourceNotFoundException("bulunamadı id = "+addressId));
    }

    @PostMapping("/addCountryJson")
    public  ResponseEntity<?> createCountry(@Valid @RequestBody List<Country> countries){
        countryService.saveList(countries);

        return ResponseEntity.ok().build();
    }


    @PostMapping("/addCityJson")
    public  ResponseEntity<?> createCity(@Valid @RequestBody List<City> cities){
        cityService.saveList(cities);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/addDistrictJson")
    public  ResponseEntity<?> createDistrict(@Valid @RequestBody List<District> districts){
        districtService.saveList(districts);
        return ResponseEntity.ok().build();
    }
}
