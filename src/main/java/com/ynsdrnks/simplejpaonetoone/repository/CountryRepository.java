package com.ynsdrnks.simplejpaonetoone.repository;

import com.ynsdrnks.simplejpaonetoone.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("countryRepository")
public interface CountryRepository  extends JpaRepository<Country,Integer>{



}
