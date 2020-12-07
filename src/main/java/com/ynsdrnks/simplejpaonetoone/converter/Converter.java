package com.ynsdrnks.simplejpaonetoone.converter;

import com.ynsdrnks.simplejpaonetoone.dto.*;
import com.ynsdrnks.simplejpaonetoone.entity.*;
import com.ynsdrnks.simplejpaonetoone.service.CountryService;
import com.ynsdrnks.simplejpaonetoone.service.impl.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class Converter {

    ModelMapper modelMapper = new ModelMapper();

    public  AdressDto adressConvertToDto(Adress adress){
        AdressDto adressDto = new AdressDto();
        modelMapper.map(adress,adressDto);
        return adressDto;
    }

    public CalisanDto calisanConvertToDto(Calisan calisan){
        CalisanDto calisanDto = new CalisanDto();
        modelMapper.map(calisan,calisanDto);
        return  calisanDto;
    }

    public MoreInfoDto moreInfoConvertToDto(MoreInfo moreInfo){
        MoreInfoDto moreInfoDto = new MoreInfoDto();
        modelMapper.map(moreInfo,moreInfoDto);
        return  moreInfoDto;
    }

    public CountryDto countryConvertToDto(Country country){
        CountryDto countryDto = new CountryDto();
        modelMapper.map(country,countryDto);
        return countryDto;
    }

    public DistrictsDto districtsConvertToDto(Districts districts){
        DistrictsDto districtsDto = new DistrictsDto();
        modelMapper.map(districts,districtsDto);
        return districtsDto;
    }

    public CityDto cityConvertToDto(City city){
        CityDto cityDto = new CityDto();
        modelMapper.map(city,cityDto);
        return cityDto;
    }
    public  Adress adressConvertToEntity(AdressDto adressDto){
        return modelMapper.map(adressDto,Adress.class);
    }

    public Calisan calisanConvertToEntity(CalisanDto calisanDto){
        return modelMapper.map(calisanDto,Calisan.class);
    }

    public  MoreInfo moreInfoConvertToEntity(MoreInfoDto moreInfoDto){
        return modelMapper.map(moreInfoDto,MoreInfo.class);
    }

    public City cityConvertToEntity(CityDto cityDto){
        return modelMapper.map(cityDto,City.class);
    }
    public Country countryConvertToEntity(CountryDto countryDto){
        return modelMapper.map(countryDto,Country.class);
    }
    public Districts districtsConvertToEntity(DistrictsDto  districtsDto){
        return modelMapper.map(districtsDto,Districts.class);
    }

    public List<CalisanDto> calisanListConvertToDtoList(List<Calisan> calisanList){
        List<CalisanDto> calisanDtos = calisanList.stream().map(calisan -> modelMapper.map(calisan,CalisanDto.class)).collect(Collectors.toList());
        return calisanDtos;
    }

    public List<CountryDto> countryListConvertToDtoList(List<Country> countryList){
        List<CountryDto> countryDtos= countryList.stream().map(country -> modelMapper.map(country,CountryDto.class)).collect(Collectors.toList());
        return countryDtos;
    }
    public List<CityDto> cityListConvertToDtoList(List<City> cityList){
        List<CityDto> cityDtos= cityList.stream().map(city -> modelMapper.map(city,CityDto.class)).collect(Collectors.toList());
        return cityDtos;
    }
    public List<DistrictsDto> districtsListConvertToDtoList(List<Districts> districtsList){
        List<DistrictsDto> districtsDtos= districtsList.stream().map(districts -> modelMapper.map(districts,DistrictsDto.class)).collect(Collectors.toList());
        return districtsDtos;
    }

}
