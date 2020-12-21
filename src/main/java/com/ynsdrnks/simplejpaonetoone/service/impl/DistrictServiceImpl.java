package com.ynsdrnks.simplejpaonetoone.service.impl;

import com.ynsdrnks.simplejpaonetoone.entity.District;
import com.ynsdrnks.simplejpaonetoone.repository.DistrictRepository;
import com.ynsdrnks.simplejpaonetoone.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DistrictServiceImpl  implements DistrictService {

    @Qualifier("districtRepository")
    @Autowired
    private DistrictRepository districtRepository;

    @Override
    public List<District> findByCity(int id) {
        return districtRepository.findByCity(id);
    }

    @Override
    public void saveList(Iterable<District> districts) {
        districtRepository.saveAll(districts);
    }

    @Override
    public List<District> findAll() {
        return districtRepository.findAll();
    }

    @Override
    public District findDistrictById(int id) {
        return districtRepository.getOne(id);
    }
}
