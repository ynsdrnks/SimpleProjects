package com.ynsdrnks.simplejpaonetoone.controller;

import com.ynsdrnks.simplejpaonetoone.converter.Converter;
import com.ynsdrnks.simplejpaonetoone.entity.*;
import com.ynsdrnks.simplejpaonetoone.service.impl.AdressServiceImpl;
import com.ynsdrnks.simplejpaonetoone.service.impl.CalisanServiceImpl;
import com.ynsdrnks.simplejpaonetoone.service.impl.CountryServiceImpl;
import com.ynsdrnks.simplejpaonetoone.service.impl.MoreInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CalisanController {
    @Autowired
    CalisanServiceImpl calisanService;
    @Autowired
    MoreInfoServiceImpl infoService;
    @Autowired
    AdressServiceImpl adressService;
    @Autowired
    Converter converter;
    @Autowired
    CountryServiceImpl countryService;

    @RequestMapping("/")
    public String index(Model model){
        List<Calisan> listCalisans = calisanService.listAllCalisans();
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
        Long id = calisan.getClsnId();
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
        infoService.saveInfo(moreInfo);
        calisan.setMoreInfo(moreInfo);
        return "redirect:/";
    }

    @RequestMapping(value = "delete/{clsnId}",method = RequestMethod.GET)
    public String delete(@PathVariable("clsnId") Long id){
        calisanService.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/edit/{clsnId}")
    public ModelAndView editCalisan(@PathVariable(name = "clsnId") Long id){
    ModelAndView mav =new ModelAndView("edit-calisan");
    Calisan calisan = calisanService.getByCalisanId(id);
    mav.addObject("calisan",calisan);

    return mav;
 }

    @GetMapping("/addInfo/{clsnId}")
    public ModelAndView addInfo(@PathVariable(name = "clsnId") Long id){
        ModelAndView mav =new ModelAndView("addInfo");
        if(infoService.getInfoById(id)!=null){
        MoreInfo moreInfo = infoService.getInfoById(id);
        infoService.saveInfo(moreInfo);
        Calisan calisan = calisanService.getByCalisanId(id);
        calisan.setMoreInfo(moreInfo);
        calisanService.save(calisan);
        mav.addObject("moreInfo",moreInfo);
        return mav;
        }

        else {
            MoreInfo moreInfo = new MoreInfo();
            moreInfo.setMoreInfoId(id);
            infoService.saveInfo(moreInfo);
            Calisan calisan = calisanService.getByCalisanId(id);
            calisan.setMoreInfo(moreInfo);
            calisanService.save(calisan);
            mav.addObject("moreInfo", moreInfo);
            return mav;
        }
    }


    @RequestMapping(value = "/addAdress",method = RequestMethod.POST)
    public String saveAdress(@ModelAttribute("adress") Adress adress) {
        Calisan calisan = calisanService.getByCalisanId(adress.getCalisanId());
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

    @RequestMapping("/listAdresses/{clsnId}")
    public String listAdresses(Model model,@PathVariable(name = "clsnId") Long id){
        List<Adress> adressList = calisanService.getByCalisanId(id).getAdresses();
        Adress adress = new Adress();
        adress.setCalisanId(id);
        if(adressList.size()==0){
            model.addAttribute("adress",adress);
            return "new-adress";
        }
        else
        model.addAttribute("listAdresses",adressList);
        return "list-adresses";
    }

//    @GetMapping("/listAdresses/addAdress/{clsnId}")
//    public String newAdress(Model model,@PathVariable(name = "clsnId")Long id){
//        Adress adress = new Adress();
//        adress.setCalisanId(id);
//        model.addAttribute("adress",adress);
//        return "new-adress";
//    }
    @RequestMapping(value = "/saveAdress/{clsnId}",method = RequestMethod.POST)
    public String saveAdresss(@ModelAttribute("adress") Adress adress) {
        calisanService.getByCalisanId(adress.getCalisanId()).getAdresses().add(adress);
        adressService.saveAdress(adress);
        return "redirect:/listAdresses/{clsnId}";
    }

    @GetMapping("/editAdress/{calisanId}/{adressId}")
    public ModelAndView editAdress(@PathVariable(name = "calisanId")Long calisanId,@PathVariable("adressId")Long adressId){
        ModelAndView mav =new ModelAndView("edit-adress");
        Adress adress=adressService.getAdressById(adressId);
        calisanId=adress.getCalisanId();
        mav.addObject("adress",adress);
        return mav;
    }

    @RequestMapping(value = "/deleteAdress/{clsnId}/{adressId}")
    public String deleteAdresss(@PathVariable("adressId") Long addressId) {
        adressService.deleteAdressById(addressId);
        return "redirect:/listAdresses/{clsnId}";
    }
    @RequestMapping(value = "/updateAdress/{clsnId}",method = RequestMethod.POST)
    public String updateAdresss(@ModelAttribute("adress") Adress adress){
        adressService.saveAdress(adress);
        return "redirect:/listAdresses/{clsnId}";
    }

    @GetMapping("/listAdresses/addAdress/{clsnId}")
    public String adresssad(Model model,@PathVariable(name ="clsnId")Long id){
        Adress adress = new Adress();
        adress.setCalisanId(id);
        model.addAttribute("countryList",countryService.findAllCountries());
        return "addressdropdown";
    }

    //dropdown
    @RequestMapping(value = "/saveAdressDropDown/{clsnId}",method = RequestMethod.POST)
    public ModelAndView saveAdresss(@PathVariable(name = "clsnId") Long clsnId,@ModelAttribute("address")@RequestBody Adress adress) {
        ModelAndView mav = new ModelAndView();
        calisanService.getByCalisanId(adress.getCalisanId()).getAdresses().add(adress);
        adressService.saveAdress(adress);
        mav.addObject("addressList",adressService.getAdressById(clsnId));
        return  mav;
    }

}

