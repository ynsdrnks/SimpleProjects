package com.ynsdrnks.simplejpaonetoone.service;

import com.ynsdrnks.simplejpaonetoone.entity.AddressDropDown;
import com.ynsdrnks.simplejpaonetoone.entity.Adress;

import java.util.List;

public interface AddressDropDownService {
    List<AddressDropDown> adressesByCalisanId(Long id);
    void   deleteAdressById(Long id);
    void   saveAdress(AddressDropDown adress);
    AddressDropDown getAdressById(Long id);
    List<AddressDropDown> listAllAdress();
    public AddressDropDown findAdressesByCalisanId(Long id);

}
