package com.ynsdrnks.simplejpaonetoone.service.impl;

import com.ynsdrnks.simplejpaonetoone.entity.AddressDropDown;
import com.ynsdrnks.simplejpaonetoone.entity.Adress;
import com.ynsdrnks.simplejpaonetoone.repository.AddressDropDownRepository;
import com.ynsdrnks.simplejpaonetoone.service.AddressDropDownService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressDropDownImpl implements AddressDropDownService {

   @Autowired
   private final AddressDropDownRepository dropDownRepo;
   @Autowired
   private final CalisanServiceImpl calisanService;

    @Override
    public List<AddressDropDown> adressesByCalisanId(Long id) {
        return calisanService.getByCalisanId(id).getAddressDropDowns();
    }

    @Override
    public void deleteAdressById(Long id) {
    dropDownRepo.deleteById(id);
    }

    @Override
    public void saveAdress(AddressDropDown adress) {
    dropDownRepo.save(adress);
    }

    @Override
    public AddressDropDown getAdressById(Long id) {
        Optional<AddressDropDown> adress = dropDownRepo.findById(id);
        if(adress.isEmpty()){
            return null;
        }
        return adress.get();
    }

    @Override
    public List<AddressDropDown> listAllAdress() {
        return dropDownRepo.findAll();
    }

    @Override
    public AddressDropDown findAdressesByCalisanId(Long id) {
        List<AddressDropDown> tempAdressList = dropDownRepo.findAll();
        for (AddressDropDown adress : tempAdressList) {
            if (adress.getCalisanId().equals(calisanService.getByCalisanId(id).getClsnId())){
                return adress;
            }
        }
        return null;
    }
}
