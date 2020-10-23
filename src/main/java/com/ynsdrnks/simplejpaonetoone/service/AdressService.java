package com.ynsdrnks.simplejpaonetoone.service;

import com.ynsdrnks.simplejpaonetoone.entity.Adress;
import com.ynsdrnks.simplejpaonetoone.entity.Calisan;
import com.ynsdrnks.simplejpaonetoone.entity.MoreInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

public interface AdressService {
    Page<Adress> getAllAdress(Pageable pageable);
    void deleteAdressById(Long id);
    Adress  updateAdress(Adress adress);
    Adress getAdressById(Long id);
    Adress createAdress(Long id, Adress adress);
}
