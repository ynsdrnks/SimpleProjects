package com.ynsdrnks.simplejpaonetoone.service;

import com.ynsdrnks.simplejpaonetoone.entity.MoreInfo;

import java.util.List;

public interface MoreInfoService {
    Iterable<MoreInfo> findAllInfos();
    void deleteInfoById(Long id);
    void  saveInfo(MoreInfo moreInfo);
    MoreInfo getInfoById(Long id);
    void deleteInfo(MoreInfo moreInfo);


}
