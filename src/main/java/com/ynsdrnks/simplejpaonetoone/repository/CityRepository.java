package com.ynsdrnks.simplejpaonetoone.repository;

import com.ynsdrnks.simplejpaonetoone.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("cityRepository")
public interface CityRepository  extends JpaRepository<City, Integer> {

   @Query("select new City (cityId,cityName) from City where country.countryId = :id")
   public List<City> findByCountry(@Param("id")int id);
}
