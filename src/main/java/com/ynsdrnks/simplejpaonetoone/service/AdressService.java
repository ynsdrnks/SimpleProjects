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
    List<Adress> adressesByCalisanId(Long id);
    void   deleteAdressById(Long id);
    void   saveAdress(Adress adress);
    Adress getAdressById(Long id);
    List<Adress> listAllAdress();
    public Adress findAdressesByCalisanId(Long id);
}
