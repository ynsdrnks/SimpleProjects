package com.ynsdrnks.simplejpaonetoone.controller;

import com.ynsdrnks.simplejpaonetoone.converter.Converter;
import com.ynsdrnks.simplejpaonetoone.dto.CalisanDto;
import com.ynsdrnks.simplejpaonetoone.entity.Calisan;
import com.ynsdrnks.simplejpaonetoone.exception.ResourceNotFoundException;
import com.ynsdrnks.simplejpaonetoone.repository.CalisanRepository;
import com.ynsdrnks.simplejpaonetoone.service.DistrictService;
import com.ynsdrnks.simplejpaonetoone.service.impl.CalisanServiceImpl;
import com.ynsdrnks.simplejpaonetoone.service.impl.CityServiceImpl;
import com.ynsdrnks.simplejpaonetoone.service.impl.CountryServiceImpl;
import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/calisanApi")
public class CalisanRestController {


    @Autowired
    private Converter converter;

    @Autowired
    private CalisanRepository calisanRepo;

    @Autowired
    private CalisanServiceImpl calisanService;

    @GetMapping("/calisanList")
    ResponseEntity<?> getCalisans(){
        return new ResponseEntity<List<CalisanDto>>(converter.calisanListConvertToDtoList((List<Calisan>)calisanService.listAllCalisans()), HttpStatus.OK);
    }

    @GetMapping(value = "/calisanList/{clsnId}")
    ResponseEntity<?> getCalisanById(@PathVariable("clsnId") long id){
        try {
            Calisan calisan = calisanService.getByCalisanId(id);

            return ResponseEntity.ok(converter.calisanConvertToDto(calisan));
        }
        catch (ResourceNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    @PutMapping("/calisanList/{clsnId}")
    public CalisanDto updateCalisan(@PathVariable Long clsnId,
                                    @Valid @RequestBody Calisan calisanRequest) {
        return calisanRepo.findById(clsnId)
                .map(calisan -> {
                    calisan.setClsnEmail(calisanRequest.getClsnEmail());
                    calisan.setClsnLastName(calisanRequest.getClsnLastName());
                    calisan.setClsnFirstName(calisanRequest.getClsnFirstName());
                    calisanService.save(calisan);
                    return converter.calisanConvertToDto(calisan);

                }).orElseThrow(()-> new ResourceNotFoundException("bulunamadı"+clsnId));
    }

    @PostMapping("/calisanList")
    public CalisanDto createCalisan(@Valid @RequestBody Calisan calisan){
        calisanRepo.save(calisan);
        return converter.calisanConvertToDto(calisan);
    }

    @DeleteMapping("/calisanList/{clsnId}")
    public ResponseEntity<?> deleteCalisan(@PathVariable("clsnId") long clsnId){
        return calisanRepo.findById(clsnId).map(calisan -> {
            calisanService.deleteById(clsnId);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("bulunamadı id = "+clsnId));
    }


}
