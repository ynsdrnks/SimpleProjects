package com.ynsdrnks.simplejpaonetoone.service.impl;

import com.ynsdrnks.simplejpaonetoone.entity.Calisan;
import com.ynsdrnks.simplejpaonetoone.entity.MoreInfo;
import com.ynsdrnks.simplejpaonetoone.repository.MoreInfoRepository;
import com.ynsdrnks.simplejpaonetoone.service.MoreInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class MoreInfoServiceImpl implements MoreInfoService {

    @Autowired
    private final MoreInfoRepository infoRepo;

    @Override
    public void deleteInfoById(Long id) {
        infoRepo.deleteById(id);
    }

    @Override
    public void saveInfo(MoreInfo moreInfo) {
    infoRepo.save(moreInfo);
    }

    @Override
    public MoreInfo getInfoById(Long id) {
        if (infoRepo.findById(id).isEmpty()){
            return null;
        }
        return  infoRepo.getOne(id);
    }

    @Override
    public void deleteInfo(MoreInfo moreInfo) {
    infoRepo.delete(moreInfo);
    }
}