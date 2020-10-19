package com.ynsdrnks.simplejpaonetoone.repository;

import com.ynsdrnks.simplejpaonetoone.entity.Calisan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalisanRepository extends JpaRepository<Calisan,Long> {

}
