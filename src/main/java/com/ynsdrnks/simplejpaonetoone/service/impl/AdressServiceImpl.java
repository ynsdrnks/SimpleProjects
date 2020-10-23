package com.ynsdrnks.simplejpaonetoone.service.impl;

import com.ynsdrnks.simplejpaonetoone.entity.Adress;
import com.ynsdrnks.simplejpaonetoone.entity.Calisan;
import com.ynsdrnks.simplejpaonetoone.entity.MoreInfo;
import com.ynsdrnks.simplejpaonetoone.repository.AdressRepository;
import com.ynsdrnks.simplejpaonetoone.service.AdressService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdressServiceImpl implements AdressService {

    @Autowired
    private final AdressRepository adressrepo;


    @Override
    public Page<Adress> getAllAdress(Pageable pageable) {
        List<Adress> adresses = adressrepo.findAll();
        return  new PageImpl<>(adresses,pageable,adresses.size());
    }

    @Override
    public void deleteAdressById(@PathVariable(value = "adress_id") Long id) {
    adressrepo.deleteById(id);
    }



    @Override
    public Adress getAdressById(@PathVariable(value = "adress_id") Long id) {
        Optional<Adress> adress = adressrepo.findById(id);
        if(!adress.isPresent()){
        return null;
        }
        return adress.get();
    }

    @Override
    public Adress createAdress(Long id,@Valid Adress adressReq) {

      return adressrepo.save(adressReq);
    }
    @Override
    public Adress updateAdress( Adress adress){

        return adressrepo.save(adress);
    }

}
