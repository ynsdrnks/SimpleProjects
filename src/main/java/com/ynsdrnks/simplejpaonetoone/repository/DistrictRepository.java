package com.ynsdrnks.simplejpaonetoone.repository;

import com.ynsdrnks.simplejpaonetoone.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("districtRepository")
public interface DistrictRepository extends JpaRepository<District,Integer> {

    @Query("SELECT new District(id,districtName) from  District where city.cityId = :id")
    public List<District> findByCity(@Param("id") int id);
}
