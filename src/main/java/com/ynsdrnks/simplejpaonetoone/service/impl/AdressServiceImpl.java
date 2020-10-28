package com.ynsdrnks.simplejpaonetoone.service.impl;

import com.ynsdrnks.simplejpaonetoone.entity.Adress;
import com.ynsdrnks.simplejpaonetoone.entity.Calisan;
import com.ynsdrnks.simplejpaonetoone.entity.MoreInfo;
import com.ynsdrnks.simplejpaonetoone.repository.AdressRepository;
import com.ynsdrnks.simplejpaonetoone.service.AdressService;
import com.ynsdrnks.simplejpaonetoone.service.CalisanService;
import javassist.expr.NewArray;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdressServiceImpl implements AdressService {

    @Autowired
    private final AdressRepository adressrepo;
    @Autowired
    private  final CalisanService calisanService;

    @Override
    public List<Adress> adressesByCalisanId(Long id) {
        return  calisanService.getByCalisanId(id).getAdresses();

    }

    @Override
    public void deleteAdressById(@PathVariable(value = "adress_id") Long id) {
    adressrepo.deleteById(id);
    }


    @Override
    public Adress getAdressById(@PathVariable(value = "adress_id") Long id) {
        Optional<Adress> adress = adressrepo.findById(id);
        if(adress.isEmpty()){
        return null;
        }
        return adress.get();
    }

    @Override
    public List<Adress> listAllAdress() {
        return adressrepo.findAll();
    }

    @Override
    public Adress findAdressesByCalisanId(Long id) {
        List<Adress> tempAdressList = adressrepo.findAll();
        for (Adress adress : tempAdressList) {
            if (adress.getCalisanId().equals(calisanService.getByCalisanId(id).getClsnId())){
                return adress;
            }
        }
        return null;
    }


    @Override
    public  void saveAdress( Adress adress){
        adressrepo.save(adress);    }
}
