package com.ynsdrnks.simplejpaonetoone.controller;

import com.ynsdrnks.simplejpaonetoone.converter.Converter;
import com.ynsdrnks.simplejpaonetoone.dto.CalisanDto;
import com.ynsdrnks.simplejpaonetoone.dto.MoreInfoDto;
import com.ynsdrnks.simplejpaonetoone.entity.MoreInfo;
import com.ynsdrnks.simplejpaonetoone.exception.ResourceNotFoundException;
import com.ynsdrnks.simplejpaonetoone.repository.MoreInfoRepository;
import com.ynsdrnks.simplejpaonetoone.service.impl.CalisanServiceImpl;
import com.ynsdrnks.simplejpaonetoone.service.impl.MoreInfoServiceImpl;
import io.micrometer.core.instrument.Metrics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/info")
public class MoreInfoRestController {

    @Autowired
    private CalisanServiceImpl calisanService;
    @Autowired
    private Converter converter;

    @Autowired
    private MoreInfoRepository infoRepository;

    @Autowired
    private MoreInfoServiceImpl infoService;

    @GetMapping("/list-info")
    ResponseEntity<?> getInfos(){
        return new ResponseEntity<List<MoreInfoDto>>(converter.infoListConvertToDtoLiST((List<MoreInfo>) infoService.findAllInfos()), HttpStatus.OK);
    }

    @GetMapping(value = "/get-info/{clsnId}")
    ResponseEntity<?> getInfoById(@PathVariable("clsnId") long id){
        try {
            MoreInfo info = calisanService.getByCalisanId(id).getMoreInfo();

            return ResponseEntity.ok(converter.moreInfoConvertToDto(info));
        }

        catch (ResourceNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/update-info/{clsnId}")
    public MoreInfoDto updateInfo(@PathVariable Long clsnId,
                                  @Valid @RequestBody MoreInfoDto infoRequest) {

        calisanService.getByCalisanId(clsnId).getMoreInfo().setFatName(infoRequest.getFatName());
        calisanService.getByCalisanId(clsnId).getMoreInfo().setMotName(infoRequest.getMotName());
        calisanService.getByCalisanId(clsnId).getMoreInfo().setNumSibl(infoRequest.getNumSibl());
        infoService.saveInfo(calisanService.getByCalisanId(clsnId).getMoreInfo());
        if (calisanService.getByCalisanId(clsnId).getMoreInfo()==null){
            throw new ResourceNotFoundException("info bulunamadı"+clsnId);
        }
        else
        return converter.moreInfoConvertToDto(calisanService.getByCalisanId(clsnId).getMoreInfo());

    }

    @PostMapping("/add-info/{clsnId}")
    public MoreInfoDto addInfo(@PathVariable("clsnId")Long id,@Valid @RequestBody MoreInfoDto moreInfoReq){
    infoService.saveInfo(converter.moreInfoConvertToEntity(moreInfoReq));
    return moreInfoReq;
    }

    @DeleteMapping("/delete-info/{clsnId}")
    public ResponseEntity<?> deleteInfo(@PathVariable("clsnId") long clsnId){
        infoRepository.deleteById(calisanService.getByCalisanId(clsnId).getMoreInfo().getMoreInfoId());
        return ResponseEntity.ok().build();
    }


    }


