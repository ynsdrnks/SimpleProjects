package com.ynsdrnks.simplejpaonetoone.service;

import com.ynsdrnks.simplejpaonetoone.entity.Calisan;
import com.ynsdrnks.simplejpaonetoone.repository.CalisanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CalisanService{

    @Autowired
    private final CalisanRepository repo;

    public List<Calisan> listAll(){
        return repo.findAll();
    }
    public void save(Calisan calisan) {
        repo.save(calisan);
    }

    public  Calisan getById(Long id) {
        return repo.findById(id).get();
    }


    public void delete(Long id) {
        repo.deleteById(id);
    }
}

