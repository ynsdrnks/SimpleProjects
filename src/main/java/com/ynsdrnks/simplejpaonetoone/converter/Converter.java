package com.ynsdrnks.simplejpaonetoone.converter;

import com.ynsdrnks.simplejpaonetoone.dto.*;
import com.ynsdrnks.simplejpaonetoone.entity.*;
import com.ynsdrnks.simplejpaonetoone.service.impl.CityServiceImpl;
import com.ynsdrnks.simplejpaonetoone.service.impl.CountryServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class Converter {

    ModelMapper modelMapper = new ModelMapper();
    @Autowired
    CountryServiceImpl countryService;
    @Autowired
    CityServiceImpl cityService;

    public  AddressDropDownDto adressConvertToDto(AddressDropDown adress){
        AddressDropDownDto addressDropDownDto = modelMapper.map(adress,AddressDropDownDto.class);
        return addressDropDownDto;
    }

    public AddressDropDownDto adresConvertToDto(AddressDropDown addressDropDown){
        AddressDropDownDto dropDownDto = new AddressDropDownDto();
        dropDownDto.setCalisan_id(addressDropDown.getCalisanId());
        dropDownDto.setCountry(countryConvertToDto(addressDropDown.getCountry()));
        dropDownDto.setCity(cityConvertToDto(addressDropDown.getCity()));
        dropDownDto.setDistrict(districtsConvertToDto(addressDropDown.getDistrict()));
        dropDownDto.setAddress_details(addressDropDown.getAddressDetails());
        return dropDownDto;
    }

    public CalisanDto calisanConvertToDto(Calisan calisan){
        CalisanDto calisanDto = new CalisanDto();
        calisanDto.setClsn_id(calisan.getClsnId());
        calisanDto.setClsn_email(calisan.getClsnEmail());
        calisanDto.setClsn_firstName(calisan.getClsnFirstName());
        calisanDto.setClsn_lastName(calisan.getClsnLastName());
        return  calisanDto;
    }

    public MoreInfoDto moreInfoConvertToDto(MoreInfo moreInfo){
        MoreInfoDto moreInfoDto = new MoreInfoDto();
        moreInfoDto.setNum_sibl(moreInfo.getNumSibl());
        moreInfoDto.setMot_name(moreInfo.getMotName());
        moreInfoDto.setMoreinfo_id(moreInfo.getMoreInfoId());
        moreInfoDto.setFat_name(moreInfo.getFatName());
        return  moreInfoDto;
    }

    public CountryDto countryConvertToDto(Country country){
        CountryDto countryDto = new CountryDto();
        countryDto.setCountry_id(country.getCountryId());
        countryDto.setCountry_name(country.getCountryName());
        return countryDto;
    }

    public DistrictsDto districtsConvertToDto(District districts){
        DistrictsDto districtsDto = new DistrictsDto();
        districtsDto.setDistrict_id(districts.getDistrictId());
        districtsDto.setDistrict_name(districts.getDistrictName());
        return districtsDto;
    }

    public CityDto cityConvertToDto(City city){
        CityDto cityDto = new CityDto();
        cityDto.setCity_id(city.getCityId());
        cityDto.setCity_name(city.getCityName());
        return cityDto;
    }
//    public  Adress adressConvertToEntity(AdressDto adressDto){
//        return modelMapper.map(adressDto,Adress.class);
//    }

    public Calisan calisanDtoConvertToEntity(CalisanDto calisanDto){
        Calisan calisan = new Calisan();
        calisan.setClsnId(calisanDto.getClsn_id());
        calisan.setClsnFirstName(calisanDto.getClsn_firstName());
        calisan.setClsnLastName(calisanDto.getClsn_lastName());
        calisan.setClsnEmail(calisanDto.getClsn_email());
        return  calisan;
    }

    public  MoreInfo moreInfoConvertToEntity(MoreInfoDto moreInfoDto){
        MoreInfo moreInfo = new MoreInfo();
        moreInfo.setMoreInfoId(moreInfoDto.getMoreinfo_id());
        moreInfo.setNumSibl(moreInfoDto.getNum_sibl());
        moreInfo.setMotName(moreInfoDto.getMot_name());
        moreInfo.setFatName(moreInfoDto.getFat_name());
        return moreInfo;
    }

    public City cityConvertToEntity(CityDto cityDto){
        City city = new City();
        city.setCityId(cityDto.getCity_id());
        city.setCityName(cityDto.getCity_name());
        return city;
    }

    public Country countryConvertToEntity(CountryDto countryDto){
        Country country = new Country();
        country.setCountryName(countryDto.getCountry_name());
        country.setCities(countryService.findCountryById(countryDto.getCountry_id()).getCities());
        country.setCountryId(countryDto.getCountry_id());
        return country;
    }

    public District districtsConvertToEntity(DistrictsDto  districtsDto){
        District district = new District();
        district.setDistrictName(districtsDto.getDistrict_name());
        district.setDistrictId(districtsDto.getDistrict_id());
        return district;
    }

    public List<CalisanDto> calisanListConvertToDtoList(List<Calisan> calisanList){

        return calisanList.stream().map(this::calisanConvertToDto).collect(Collectors.toList());
    }

    public List<CountryDto> countryListConvertToDtoList(List<Country> countryList){

        return countryList.stream().map(this::countryConvertToDto).collect(Collectors.toList());
    }

    public List<CityDto> cityListConvertToDtoList(List<City> cityList){

        return cityList.stream().map(this::cityConvertToDto).collect(Collectors.toList());
    }

    public List<DistrictsDto> districtsListConvertToDtoList(List<District> districtsList){
       return districtsList.stream().map(this::districtsConvertToDto).collect(Collectors.toList());
    }
//    public List<AdressDto> adressListConvertToDtoList(List<Adress> adressList){
//       return adressList.stream().map(thi)
//    }

    public List<AddressDropDownDto> adressDropdownListConvertToDtoList(List<AddressDropDown> adressList){
          return adressList.stream().map(this::adresConvertToDto).collect(Collectors.toList());
    }
}
