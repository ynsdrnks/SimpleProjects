package com.ynsdrnks.simplejpaonetoone.controller;


import com.ynsdrnks.simplejpaonetoone.converter.Converter;
import com.ynsdrnks.simplejpaonetoone.dto.*;
import com.ynsdrnks.simplejpaonetoone.entity.*;
import com.ynsdrnks.simplejpaonetoone.exception.ResourceNotFoundException;
import com.ynsdrnks.simplejpaonetoone.repository.AddressDropDownRepository;
import com.ynsdrnks.simplejpaonetoone.repository.CityRepository;
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
@RequestMapping("/api/address")
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

    @GetMapping("/list-country")
    ResponseEntity<?> getCountry(){
        return new ResponseEntity<List<CountryDto>>(converter.countryListConvertToDtoList((List<Country>)countryService.findAllCountries()), HttpStatus.OK);
    }
    @GetMapping("/all-cities")
    ResponseEntity<?> getAllCities(){
        return new ResponseEntity<List<CityDto>>(converter.cityListConvertToDtoList((List<City>)cityRepository.findAll()),HttpStatus.OK);
    }

    @GetMapping("/city-by-country")
    ResponseEntity<?> getCities(@RequestParam("country_id")Integer  countryId){
        return new ResponseEntity<List<CityDto>>(converter.cityListConvertToDtoList(cityService.findByCountry(countryId)), HttpStatus.OK);
    }


    @GetMapping("/districts-by-city")
    ResponseEntity<?> getDistricts(@RequestParam("city_id")Integer cityId){
        return new ResponseEntity<List<DistrictsDto>>(converter.districtsListConvertToDtoList((districtService.findByCity(cityId))), HttpStatus.OK);
    }


//    @GetMapping("/allAddress")
//    ResponseEntity<?> getAllAddress(@RequestParam("calisan_id")Long calisanId){
//        List<AddressDropDown> adressList = calisanService.getByCalisanId(calisanId).getAddressDropDowns();
//        return new ResponseEntity<List<AddressDropDownDto>>(converter.adressDropdownListConvertToDtoList(adressList),HttpStatus.OK);
//    }

    @GetMapping("/all-address/{empId}")
    ResponseEntity<?> getAllAddressCalisan(@PathVariable("empId") Long calisanId){
        List<AddressDropDown> adressList = calisanService.getByCalisanId(calisanId).getAddressDropDowns();
        return new ResponseEntity<List<AddressDropDownDto>>(converter.adressDropdownListConvertToDtoList(adressList),HttpStatus.OK);
    }

    @GetMapping("/all-address/{empId}/{addressId}")
    public  AddressDropDownDto getOneAddress(@PathVariable("empId")Long calisanId, @PathVariable("addressId")Long addressId){
        return converter.adresConvertToDto(addressService.getAdressById(addressId));
    }


    @PutMapping("/update-address/{clsnId}/{addressId}")
    public  AddressDropDownDto updateAddress(@PathVariable("clsnId") Long clsnId,@PathVariable("addressId")Long addressId,
                                             @Valid @RequestBody AddressDropDownDto addressDropDownDtoReq){
        return addressDropDownRepo.findById(addressId)
                .map(addressDropDown -> {
                    addressDropDown.setAddressDetails(addressDropDownDtoReq.getAdressDetails());
                    addressDropDown.setDistrict(districtService.findDistrictById(addressDropDownDtoReq.getDistrict().getDistrictId()));
                    addressDropDown.setCity(districtService.findDistrictById(addressDropDownDtoReq.getDistrict().getDistrictId()).getCity());
                    addressDropDown.setCountry(districtService.findDistrictById(addressDropDownDtoReq.getDistrict().getDistrictId()).getCity().getCountry());
                    addressDropDown.setCalisanId(clsnId);
                    addressService.saveAdress(addressDropDown);
                    return converter.adresConvertToDto(addressDropDown);
                }).orElseThrow(() -> new ResourceNotFoundException("not found : "+addressId));
    }

    @PostMapping("/add-address/{clsnId}")
    public  AddressDropDownDto createAddress(@PathVariable("clsnId")Long calisanId,@Valid @RequestBody AddressDropDownDto addressDropDownDtoReq){
        AddressDropDown addressDropDown = new AddressDropDown();
        addressDropDown.setCalisanId(calisanId);
        addressDropDown.setAddressDetails(addressDropDownDtoReq.getAdressDetails());
        addressDropDown.setDistrict(districtService.findDistrictById(addressDropDownDtoReq.getDistrict().getDistrictId()));
        addressDropDown.setCity(districtService.findDistrictById(addressDropDownDtoReq.getDistrict().getDistrictId()).getCity());
        addressDropDown.setCountry(districtService.findDistrictById(addressDropDownDtoReq.getDistrict().getDistrictId()).getCity().getCountry());
        calisanService.getByCalisanId(calisanId).getAddressDropDowns().add(addressDropDown);
        addressService.saveAdress(addressDropDown);
        return converter.adresConvertToDto(addressDropDown);
    }

    @DeleteMapping("/delete-address/{clsnId}/{addressId}")
    public ResponseEntity<?> deleteAddress(@PathVariable("clsnId") long clsnId,@PathVariable("addressId") long addressId){
        return addressDropDownRepo.findById(addressId).map(addressDropDown -> {
            addressService.deleteAdressById(addressId);
            return ResponseEntity.ok().build();
        }).orElseThrow(()-> new ResourceNotFoundException("adres bulunamadı id = "+addressId));
    }

    @PostMapping("/add-country-json")
    public  ResponseEntity<?> createCountry(@Valid @RequestBody List<CountryDto> countriesReq){
        countryService.saveList(converter.countryDtoListConvertToEntityList(countriesReq));

        return ResponseEntity.ok().build();
    }


    @PostMapping("/add-city-json")
    public  ResponseEntity<?> createCity(@Valid @RequestBody List<CityDto> cityDtos){
        cityService.saveList(converter.cityDtoListConvertToList(cityDtos));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/add-district-json")
    public  ResponseEntity<?> createDistrict(@Valid @RequestBody List<DistrictsDto> districtsDtos){
        districtService.saveList(converter.districtDtoListConvertToEntityList(districtsDtos));
        return ResponseEntity.ok().build();
    }

}
