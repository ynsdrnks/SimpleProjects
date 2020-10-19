package com.ynsdrnks.simplejpaonetoone.controller;

import com.ynsdrnks.simplejpaonetoone.entity.Calisan;
import com.ynsdrnks.simplejpaonetoone.entity.MoreInfo;
import com.ynsdrnks.simplejpaonetoone.service.CalisanService;
import com.ynsdrnks.simplejpaonetoone.service.MoreInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.constraints.Null;

@Controller
public class CalisanController {
    @Autowired
    CalisanService calisanService;
    @Autowired
    MoreInfoService infoService;
 @RequestMapping("/")
    public String index(Model model){
        List<Calisan> listCalisans = calisanService.listAll();
        model.addAttribute("listCalisans",listCalisans);
        return "home";
    }
    @GetMapping("/new")
    public String newCalisan(Model model){
        Calisan calisan = new Calisan();
        model.addAttribute("calisan",calisan);
        return "new-calisan";
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String save(@ModelAttribute("calisan") Calisan calisan) {
        calisanService.save(calisan);
        Long id = calisan.getId();
        if (infoService.getInfoById(id)!= null){
            MoreInfo moreInfo = infoService.getInfoById(id);
            calisan.setMoreInfo(moreInfo);
            calisanService.save(calisan);
    }
        return "redirect:/";
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String addInfo(@ModelAttribute("moreInfo") MoreInfo moreInfo,Calisan calisan) {
        infoService.save(moreInfo);
        calisan.setMoreInfo(moreInfo);
        return "redirect:/";
    }

    @RequestMapping(value = "delete/{id}",method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id){
        calisanService.delete(id);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editCalisan(@PathVariable(name = "id") Long id){
    ModelAndView mav =new ModelAndView("edit");
    Calisan calisan = calisanService.getById(id);
    mav.addObject("calisan",calisan);

    return mav;
 }

    @GetMapping("/addInfo/{id}")
    public ModelAndView addInfo(@PathVariable(name = "id") Long id){
        ModelAndView mav =new ModelAndView("addInfo");
        MoreInfo moreInfo = new MoreInfo();
        moreInfo.setMoreInfoId(id);
        infoService.save(moreInfo);
        Calisan calisan = calisanService.getById(id);
        calisan.setMoreInfo(moreInfo);
        calisanService.save(calisan);
        mav.addObject("moreInfo",moreInfo);
        return mav;
    }




}

