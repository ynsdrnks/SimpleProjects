package com.ynsdrnks.simplejpaonetoone.service;

import com.ynsdrnks.simplejpaonetoone.entity.Calisan;

import java.util.List;

public interface CalisanService {
List<Calisan> listAll();
void  save(Calisan calisan);
Calisan getById(Long id);
void deleteById(Long id);
}
