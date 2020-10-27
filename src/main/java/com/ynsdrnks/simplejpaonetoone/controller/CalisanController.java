package com.ynsdrnks.simplejpaonetoone.controller;

import com.ynsdrnks.simplejpaonetoone.entity.Adress;
import com.ynsdrnks.simplejpaonetoone.entity.Calisan;
import com.ynsdrnks.simplejpaonetoone.entity.MoreInfo;
import com.ynsdrnks.simplejpaonetoone.service.AdressService;
import com.ynsdrnks.simplejpaonetoone.service.impl.AdressServiceImpl;
import com.ynsdrnks.simplejpaonetoone.service.impl.CalisanServiceImpl;
import com.ynsdrnks.simplejpaonetoone.service.impl.MoreInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class CalisanController {
    @Autowired
    CalisanServiceImpl calisanService;
    @Autowired
    MoreInfoServiceImpl infoService;
    @Autowired
    AdressServiceImpl adressService;
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

    @RequestMapping(value = "/saveCalisan",method = RequestMethod.POST)
    public String save(@ModelAttribute("calisan") Calisan calisan) {
        calisanService.save(calisan);
        Long id = calisan.getId();
        if (infoService.getInfoById(id)!= null){
            MoreInfo moreInfo = infoService.getInfoById(id);
            calisan.setMoreInfo(moreInfo);
            calisanService.save(calisan);
            }
        if (adressService.findAdressesByCalisanId(id)!=null) {
            List<Adress> adressList1 = new ArrayList<>();
            adressList1.add(adressService.findAdressesByCalisanId(id));
            calisan.setAdresses(adressList1);
            calisanService.save(calisan);
        }
        return "redirect:/";
}


    @RequestMapping(value = "/addInfo",method = RequestMethod.POST)
    public String addInfo(@ModelAttribute("moreInfo") MoreInfo moreInfo,Calisan calisan) {
        infoService.save(moreInfo);
        calisan.setMoreInfo(moreInfo);
        return "redirect:/";
    }

    @RequestMapping(value = "delete/{id}",method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id){
        calisanService.deleteById(id);
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
        if(infoService.getInfoById(id)!=null){
        MoreInfo moreInfo = infoService.getInfoById(id);
        infoService.save(moreInfo);
        Calisan calisan = calisanService.getById(id);
        calisan.setMoreInfo(moreInfo);
        calisanService.save(calisan);
        mav.addObject("moreInfo",moreInfo);
        return mav;
        }

        else {
            MoreInfo moreInfo = new MoreInfo();
            moreInfo.setMoreInfoId(id);
            infoService.save(moreInfo);
            Calisan calisan = calisanService.getById(id);
            calisan.setMoreInfo(moreInfo);
            calisanService.save(calisan);
            mav.addObject("moreInfo", moreInfo);
            return mav;
        }
    }

    @GetMapping("/addAdress/{id}")
    public String addAddres(@PathVariable(name = "id")Long id){
        return "list-adresses";
    }

    @RequestMapping(value = "/addAdress",method = RequestMethod.POST)
    public String saveAdress(@ModelAttribute("adress") Adress adress) {
        Calisan calisan = calisanService.getById(adress.getCalisan_id());
        if (calisan.getAdresses().isEmpty()){
            List<Adress> adresses = new ArrayList<>();
            adresses.add(adress);
            calisan.setAdresses(adresses);
            calisanService.save(calisan);
        }
        else
        calisan.getAdresses().add(adress);
        calisanService.save(calisan);

        return "redirect:/";
    }

    @RequestMapping("/listAdresses/{id}")
    public String listAdresses(Model model,@PathVariable(name = "id") Long id){
        List<Adress> adressList = calisanService.getById(id).getAdresses();
        Adress adress = new Adress();
        adress.setCalisan_id(id);
        if(adressList.size()==0){
            model.addAttribute("adress",adress);
            return "new-adress";
        }
        else
        model.addAttribute("listAdresses",adressList);
        return "list-adresses";
    }

    @GetMapping("/listAdresses/addAdress/{id}")
    public String newAdress(Model model,@PathVariable(name = "id")Long id){
        Adress adress = new Adress();
        adress.setCalisan_id(id);
        model.addAttribute("adress",adress);
        return "new-adress";
    }
    @RequestMapping(value = "/saveAdress",method = RequestMethod.POST)
    public String saveAdresss(@ModelAttribute("adress") Adress adress) {
        calisanService.getById(adress.getCalisan_id()).getAdresses().add(adress);
        adressService.saveAdress(adress);
        return "redirect:/";
    }

    @GetMapping("/editAdress/{id}")
    public ModelAndView editAdress(@PathVariable(name = "id") Long id){
        ModelAndView mav =new ModelAndView("edit-adress");
        Adress adress=adressService.getAdressById(id);
        mav.addObject("adress",adress);
        return mav;
    }

    @RequestMapping(value = "/updateAdress",method = RequestMethod.POST)
    public String updateAdresss(@ModelAttribute("adress") Adress adress) {
        adressService.saveAdress(adress);
        return "redirect:/";
    }
    @RequestMapping(value = "/deleteAdress/{id}",method = RequestMethod.GET)
    public String deleteAdress(@PathVariable("id") Long id){
        Long tempId= adressService.getAdressById(id).getCalisan_id();
        adressService.deleteAdressById(id);
        return "redirect:/";
    }


}

