package com.ynsdrnks.simplejpaonetoone.repository;

import com.ynsdrnks.simplejpaonetoone.entity.Districts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("districtRepository")
public interface DistrictRepository extends JpaRepository<Districts,Integer> {

    @Query("SELECT new Districts(id,districtName) from  Districts where city.cityId = :id")
    public List<Districts> findByCity(@Param("id") int id);
}
