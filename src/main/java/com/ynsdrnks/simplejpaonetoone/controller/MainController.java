package com.ynsdrnks.simplejpaonetoone.controller;

import com.ynsdrnks.simplejpaonetoone.converter.Converter;
import com.ynsdrnks.simplejpaonetoone.entity.*;
import com.ynsdrnks.simplejpaonetoone.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
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
    @Autowired
    AddressDropDownImpl addressDropDownService;

    @RequestMapping("/")
    public String index(Model model){
        List<Calisan> listCalisans = calisanService.listAllCalisans();
        model.addAttribute("listCalisans",listCalisans);
        return "home";
    }

    @GetMapping("/new-employee")
    public String newCalisan(Model model){
        Calisan calisan = new Calisan();
        model.addAttribute("calisan",calisan);
        return "new-calisan";
    }

    @RequestMapping(value = "/save-employee",method = RequestMethod.POST)
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


    @RequestMapping(value = "/add-info",method = RequestMethod.POST)
    public String addInfo(@ModelAttribute("moreInfo") MoreInfo moreInfo,Calisan calisan) {
        infoService.saveInfo(moreInfo);
        calisan.setMoreInfo(moreInfo);
        return "redirect:/";
    }

    @RequestMapping(value = "delete-employee/{clsnId}",method = RequestMethod.GET)
    public String delete(@PathVariable("clsnId") Long id){
        calisanService.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/edit-employee/{clsnId}")
    public ModelAndView editCalisan(@PathVariable(name = "clsnId") Long id){
    ModelAndView mav =new ModelAndView("edit-calisan");
    Calisan calisan = calisanService.getByCalisanId(id);
    mav.addObject("calisan",calisan);

    return mav;
 }

    @GetMapping("/add-info/{clsnId}")
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


    @RequestMapping(value = "/add-address",method = RequestMethod.POST)
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


    @RequestMapping(value = "/save-address/{clsnId}",method = RequestMethod.POST)
    public String saveAdresss(@ModelAttribute("adress") AddressDropDown adress) {
        calisanService.getByCalisanId(adress.getCalisanId()).getAddressDropDowns().add(adress);
        addressDropDownService.saveAdress(adress);
        return "redirect:/list-addresses/{clsnId}";
    }

    @GetMapping("/edit-address/{calisanId}/{adressId}")
    public ModelAndView editAdress(@PathVariable(name = "calisanId")Long calisanId,@PathVariable("adressId")Long adressId){
        ModelAndView mav =new ModelAndView("edit-adress");
        AddressDropDown adress=addressDropDownService.getAdressById(adressId);
        calisanId=adress.getCalisanId();
        mav.addObject("adress",adress);
        mav.addObject("countryList",countryService.findAllCountries());
        return mav;
    }

    @RequestMapping(value = "/delete-address/{clsnId}/{adressId}")
    public String deleteAdresss(@PathVariable("adressId") Long addressId) {
        addressDropDownService.deleteAdressById(addressId);
        return "redirect:/list-addresses/{clsnId}";
    }
    @RequestMapping(value = "/update-address/{clsnId}",method = RequestMethod.POST)
    public String updateAdresss(@ModelAttribute("adress") AddressDropDown adress){
        addressDropDownService.saveAdress(adress);
        return "redirect:/list-addresses/{clsnId}";
    }

    //dropdown

    @GetMapping("/list-addresses/add-address/{clsnId}")
    public String adresssAd(Model model,@PathVariable(name ="clsnId")Long id){
        AddressDropDown addressDropDown = new AddressDropDown();
        addressDropDown.setCalisanId(id);
        model.addAttribute("countryList",countryService.findAllCountries());
        model.addAttribute("addressDropDown",addressDropDown);
        model.addAttribute("country",addressDropDown.getCountry());
        model.addAttribute("city",addressDropDown.getCity());
        model.addAttribute("district",addressDropDown.getDistrict());
        model.addAttribute("adressDetails",addressDropDown.getAddressDetails());
        return "addressdropdown";
    }

    @RequestMapping(value = "/save-addresss/{clsnId}",method = RequestMethod.POST)
    public String saveAdresss(@PathVariable(name = "clsnId") Long clsnId,@ModelAttribute("address")@RequestBody AddressDropDown addressDropDown) {
        ModelAndView mav = new ModelAndView();
        calisanService.getByCalisanId(clsnId).getAddressDropDowns().add(addressDropDown);
        addressDropDown.setCalisanId(clsnId);
        addressDropDownService.saveAdress(addressDropDown);
        return  "redirect:/list-addresses/{clsnId}";
    }

    @RequestMapping("/list-addresses/{clsnId}")
    public String listdropDownAdresses(Model model,@PathVariable(name = "clsnId") Long id){
        List<AddressDropDown> adressList = calisanService.getByCalisanId(id).getAddressDropDowns();
        AddressDropDown addressDropDown = new AddressDropDown();
        addressDropDown.setCalisanId(id);
        if(adressList.size()==0){
            model.addAttribute("adressDropDown",addressDropDown);
            return "redirect:/list-addresses/add-address/{clsnId}";
        }
        else
            model.addAttribute("listAdresses",adressList);
        return "list-adresses";
    }




}

