package com.ynsdrnks.simplejpaonetoone.service.impl;

import com.ynsdrnks.simplejpaonetoone.entity.MoreInfo;
import com.ynsdrnks.simplejpaonetoone.repository.MoreInfoRepository;
import com.ynsdrnks.simplejpaonetoone.service.MoreInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MoreInfoServiceImpl implements MoreInfoService {

    @Autowired
    private final MoreInfoRepository infoRepo;

    @Override
    public Iterable<MoreInfo> findAllInfos() {
    return infoRepo.findAll();
    }

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