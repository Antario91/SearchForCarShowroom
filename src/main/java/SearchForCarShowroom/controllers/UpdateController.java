package SearchForCarShowroom.controllers;

import SearchForCarShowroom.domain.Automobile;
import SearchForCarShowroom.domain.CarKit;
import SearchForCarShowroom.domain.CarShowroom;
import SearchForCarShowroom.domain.ManufacturingPlant;
import SearchForCarShowroom.service.CreationService;
import SearchForCarShowroom.service.SearchService;
import SearchForCarShowroom.service.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import java.util.*;

/**
 * Created by AlexandrGoloborodko on 21.08.16.
 */
@Controller
public class UpdateController {


    @Autowired
    private CreationService creationService;

    @Autowired
    private SearchService searchService;

    @Autowired
    private UpdateService updateService;




    @RequestMapping(path = "/addCarKitToAutomobile", method = RequestMethod.GET)
    public ModelAndView addCarKitToAutomobile(ModelMap model) {
        Map<String,Object> temp = searchService.getAllAutomobileAndAllCarKits();
        Set<CarKit> kitsList = new HashSet<>();
        for (CarKit kit : (List<CarKit>)temp.get("kits")) {
            kitsList.add(kit);
        }
        model.put("autosList", temp.get("autos"));
        model.put("kitsList", kitsList);
        return new ModelAndView("addCarKitToAutomobile", model);
    }

    @RequestMapping(path = "/updateAutomobileWithNewCarKits", method = RequestMethod.POST)
    public View updateAutomobileWithNewCarKits(@RequestParam("modelAuto") String autoModel, @RequestParam(value = "kits", required = false) int[] carKitIDs) {
        try {
            updateService.updateAutomobileWithNewCarKits(autoModel, carKitIDs);
        } catch (NullPointerException e) {
            return new RedirectView("/");
        }
        return new RedirectView("/");
    }




    @RequestMapping(path = "/addCarKitToCarShowroom", method = RequestMethod.GET)
    public String addCarKitToCarShowroom(ModelMap model) {
        Map<String,Object> temp = searchService.getAllCarKitsAndAllCarShowrooms();
        model.put("kitsList", (List<CarKit>)temp.get("kitsList"));
        model.put("carShowroomList", (List<CarShowroom>)temp.get("carShowroomList"));
        return "addCarKitToCarShowroom";
    }

    @RequestMapping(path = "/updateCarKitsWithNewCarShowroom", method = RequestMethod.POST)
    public View updateCarKitsWithNewCarShowroom(@RequestParam(value = "kits", required = false) int[] kitsIDs, @RequestParam(value = "showrooms", required = false) int[] showroomsIDs){
        if (kitsIDs != null && showroomsIDs != null){
            for (int i = 0; i < kitsIDs.length; i++) {
                for (int j = 0; j < showroomsIDs.length; j++) {
                    creationService.addCarShowRoomToCarKit(kitsIDs[i], showroomsIDs[j]);
                }
            }
        }
        return new RedirectView("/");
    }





    @RequestMapping(path = "/addManufacturingPlantToAutomobile", method = RequestMethod.GET)
    public String addManufacturingPlantToAutomobile (ModelMap model) {
//        List<Automobile> autos = searchService.findAllAutomobile();
//        List<ManufacturingPlant> factories = searchService.findAllManufacturingPlant();
        Map<String,Object> temp = searchService.getAllAutomobileAndAllManufacturingPlant();
        model.put("autosList", (List<Automobile>)temp.get("autosList"));
        model.put("manufacturingPlantsList", (List<ManufacturingPlant>)temp.get("manufacturingPlantsList"));
        return "addManufacturingPlantToAutomobile";
    }

    @RequestMapping(path = "/updateAutomobileWithNewManufacturingPlant", method = RequestMethod.POST)
    public View updateAutomobileWithNewManufacturingPlant (@RequestParam("modelAuto") String modelAuto, @RequestParam(name = "country", required = false) String[] factoryCountry){
        if (factoryCountry != null) {
            for (int i = 0; i < factoryCountry.length; i++) {
                creationService.addPlantToAutomobile(modelAuto, factoryCountry[i]);
            }
        }
        return new RedirectView("/");
    }
}