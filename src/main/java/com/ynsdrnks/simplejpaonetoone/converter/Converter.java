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

//    public  AddressDropDownDto adressConvertToDto(AddressDropDown adress){
//        AddressDropDownDto addressDropDownDto = modelMapper.map(adress,AddressDropDownDto.class);
//        return addressDropDownDto;
//    }

    public AddressDropDownDto adresConvertToDto(AddressDropDown addressDropDown){
        AddressDropDownDto dropDownDto = new AddressDropDownDto();
        dropDownDto.setCalisanId(addressDropDown.getCalisanId());
        dropDownDto.setCountry(countryConvertToDto(addressDropDown.getCountry()));
        dropDownDto.setCity(cityConvertToDto(addressDropDown.getCity()));
        dropDownDto.setDistrict(districtsConvertToDto(addressDropDown.getDistrict()));
        dropDownDto.setAdressDetails(addressDropDown.getAddressDetails());
        return dropDownDto;
    }

    public CalisanDto calisanConvertToDto(Calisan calisan){
        CalisanDto calisanDto = new CalisanDto();
        calisanDto.setClsnId(calisan.getClsnId());
        calisanDto.setClsnEmail(calisan.getClsnEmail());
        calisanDto.setClsnFirstName(calisan.getClsnFirstName());
        calisanDto.setClsnLastName(calisan.getClsnLastName());
        return  calisanDto;
    }

    public MoreInfoDto moreInfoConvertToDto(MoreInfo moreInfo){
        MoreInfoDto moreInfoDto = new MoreInfoDto();
        moreInfoDto.setNumSibl(moreInfo.getNumSibl());
        moreInfoDto.setMotName(moreInfo.getMotName());
        moreInfoDto.setMoreinfoId(moreInfo.getMoreInfoId());
        moreInfoDto.setFatName(moreInfo.getFatName());
        return  moreInfoDto;
    }

    public CountryDto countryConvertToDto(Country country){
        CountryDto countryDto = new CountryDto();
        countryDto.setCountryId(country.getCountryId());
        countryDto.setCountryName(country.getCountryName());
        return countryDto;
    }

    public DistrictsDto districtsConvertToDto(District districts){
        DistrictsDto districtsDto = new DistrictsDto();
        districtsDto.setDistrictId(districts.getDistrictId());
        districtsDto.setDistrictName(districts.getDistrictName());
        return districtsDto;
    }

    public CityDto cityConvertToDto(City city){
        CityDto cityDto = new CityDto();
        cityDto.setCityId(city.getCityId());
        cityDto.setCityName(city.getCityName());
        return cityDto;
    }
//    public  Adress adressConvertToEntity(AdressDto adressDto){
//        return modelMapper.map(adressDto,Adress.class);
//    }

    public Calisan calisanDtoConvertToEntity(CalisanDto calisanDto){
        Calisan calisan = new Calisan();
        calisan.setClsnId(calisanDto.getClsnId());
        calisan.setClsnFirstName(calisanDto.getClsnFirstName());
        calisan.setClsnLastName(calisanDto.getClsnLastName());
        calisan.setClsnEmail(calisanDto.getClsnEmail());
        return  calisan;
    }

    public  MoreInfo moreInfoConvertToEntity(MoreInfoDto moreInfoDto){
        MoreInfo moreInfo = new MoreInfo();
        moreInfo.setMoreInfoId(moreInfoDto.getMoreinfoId());
        moreInfo.setNumSibl(moreInfoDto.getNumSibl());
        moreInfo.setMotName(moreInfoDto.getMotName());
        moreInfo.setFatName(moreInfoDto.getFatName());
        return moreInfo;
    }

    public City cityConvertToEntity(CityDto cityDto){
        City city = new City();
        city.setCityId(cityDto.getCityId());
        city.setCityName(cityDto.getCityName());
        return city;
    }

    public Country countryConvertToEntity(CountryDto countryDto){
        Country country = new Country();
        country.setCountryName(countryDto.getCountryName());
        country.setCities(countryService.findCountryById(countryDto.getCountryId()).getCities());
        country.setCountryId(countryDto.getCountryId());
        return country;
    }

    public District districtsConvertToEntity(DistrictsDto  districtsDto){
        District district = new District();
        district.setDistrictName(districtsDto.getDistrictName());
        district.setDistrictId(districtsDto.getDistrictId());
        return district;
    }

    public List<CalisanDto> calisanListConvertToDtoList(List<Calisan> calisanList){

        return calisanList.stream().map(this::calisanConvertToDto).collect(Collectors.toList());
    }

    public List<CountryDto> countryListConvertToDtoList(List<Country> countryList){

        return countryList.stream().map(this::countryConvertToDto).collect(Collectors.toList());
    }

    public List<Country> countryDtoListConvertToEntityList(List<CountryDto> countryDtos){

        return countryDtos.stream().map(this::countryConvertToEntity).collect(Collectors.toList());

    }

    public List<CityDto> cityListConvertToDtoList(List<City> cityList){

        return cityList.stream().map(this::cityConvertToDto).collect(Collectors.toList());
    }
    public List<City> cityDtoListConvertToList(List<CityDto> cityDtos){

        return cityDtos.stream().map(this::cityConvertToEntity).collect(Collectors.toList());
    }

    public List<MoreInfoDto> infoListConvertToDtoLiST(List<MoreInfo> moreInfos){

        return moreInfos.stream().map(this::moreInfoConvertToDto).collect(Collectors.toList());
    }

    public List<DistrictsDto> districtsListConvertToDtoList(List<District> districtsList){
       return districtsList.stream().map(this::districtsConvertToDto).collect(Collectors.toList());
    }
    public List<District> districtDtoListConvertToEntityList(List<DistrictsDto> districtsDtos){
        return districtsDtos.stream().map(this::districtsConvertToEntity).collect(Collectors.toList());
    }
//    public List<AdressDto> adressListConvertToDtoList(List<Adress> adressList){
//       return adressList.stream().map(thi)
//    }

    public List<AddressDropDownDto> adressDropdownListConvertToDtoList(List<AddressDropDown> adressList){
          return adressList.stream().map(this::adresConvertToDto).collect(Collectors.toList());
    }
}
