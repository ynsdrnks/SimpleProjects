package com.ynsdrnks.simplejpaonetoone.service;

import com.ynsdrnks.simplejpaonetoone.entity.Calisan;

import java.util.List;

public interface CalisanService {
List<Calisan> listAllCalisans();
void  save(Calisan calisan);
Calisan getByCalisanId(Long id);
void deleteById(Long id);
}
