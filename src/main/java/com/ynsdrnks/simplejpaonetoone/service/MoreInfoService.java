package com.ynsdrnks.simplejpaonetoone.service;

import com.ynsdrnks.simplejpaonetoone.entity.Calisan;
import com.ynsdrnks.simplejpaonetoone.entity.MoreInfo;
import com.ynsdrnks.simplejpaonetoone.repository.MoreInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class MoreInfoService {

    @Autowired
    private final MoreInfoRepository infoRepo;
    public void  deleteInfo(Long id){
        infoRepo.deleteById(id);
    }
    public void save(MoreInfo moreInfo) {infoRepo.save(moreInfo); }

    public  MoreInfo getInfoById(Long id) {
        if(infoRepo.findById(id).isEmpty()){
            return null;
        }
        return infoRepo.findById(id).get();}
    public void saveInfoId(MoreInfo moreInfo,Long id){moreInfo.setMoreInfoId(id);}
}
