package com.ynsdrnks.simplejpaonetoone.repository;

import com.ynsdrnks.simplejpaonetoone.entity.MoreInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public  interface MoreInfoRepository  extends JpaRepository<MoreInfo,Long> {


}
