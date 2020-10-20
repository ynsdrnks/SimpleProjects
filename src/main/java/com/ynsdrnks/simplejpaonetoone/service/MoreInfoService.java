package com.ynsdrnks.simplejpaonetoone.service;

import com.ynsdrnks.simplejpaonetoone.entity.MoreInfo;

public interface MoreInfoService {

    void deleteInfoById(Long id);
    void  save(MoreInfo moreInfo);
    MoreInfo getInfoById(Long id);
    void deleteInfo(MoreInfo moreInfo);
}
