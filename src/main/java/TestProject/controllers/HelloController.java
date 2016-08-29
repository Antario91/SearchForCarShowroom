package TestProject.controllers;

import TestProject.DBInitializer.DBInitialize;
import TestProject.domain.Automobile;
import TestProject.domain.CarKit;
import TestProject.domain.CarShowroom;
import TestProject.domain.ManufacturingPlant;
import TestProject.resultOfSearch.searchObject;
import TestProject.service.creationService;
import TestProject.service.searchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import java.beans.PropertyEditor;
import java.util.*;

/**
 * Created by AlexandrGoloborodko on 21.08.16.
 */
@Controller
public class HelloController {
    @Autowired
    private DBInitialize initializer;

    @Autowired
    private creationService createService;

    @Autowired
    private searchService searchService;

    @Autowired
    @Qualifier("CarKitPropertyEditor")
    private PropertyEditor CarKitPropertyEditor;

    @Autowired
    @Qualifier("AutomobilePropertyEditor")
    private PropertyEditor AutomobilePropertyEditor;


    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String index() {
        initializer.initDB();

        return "index";
    }

    @RequestMapping(path = "/form", method = RequestMethod.GET)
    public ModelAndView form() {
        searchObject search = new searchObject();
        List<String> automobileModels = searchService.getAllAutomobileModels();
        ModelMap model = new ModelMap("searchObject", search);
        model.put("modelList", automobileModels);
        ModelAndView modelAndView = new ModelAndView("form", model);
        return modelAndView;
    }

    @RequestMapping(path = "/result", method = RequestMethod.POST)
    public ModelAndView result(@ModelAttribute("searchObject") searchObject search, ModelMap model) {
        Map<String, Integer> price = search.pricesHandler();
        List<CarKit> results = null;
        Map<Integer, List<ManufacturingPlant>> factories = null;
        Map<Integer, List<CarShowroom>> showrooms = null;

        if (search.getModel().equals("null")) {
            results = searchService.findByCostAndDescription(price, search.getDescription());
//            factories = searchService.findFactoriesOfAutomobile(results);
//            showrooms = searchService.findCarShowroomOfCarKit(results);
            model.put("searchingResults", results);
//            model.put("factories", factories);
//            model.put("showrooms", showrooms);
            return new ModelAndView("result", model);
        }

        if (search.getModel().equals("null") && price.get("minCost") == 0 && price.get("maxCost") == 1000000000) {
            results = searchService.findByAutoModelAndDescription(search.getModel(), search.getDescription());
//            factories = searchService.findFactoriesOfAutomobile(results);
//            showrooms = searchService.findCarShowroomOfCarKit(results);
            model.put("searchingResults", results);
//            model.put("factories", factories);
//            model.put("showrooms", showrooms);
            return new ModelAndView("result", model);
        }

        results = searchService.findByModelAndCostAndDescription(search.getModel(), price, search.getDescription());
//        factories = searchService.findFactoriesOfAutomobile(results);
//        showrooms = searchService.findCarShowroomOfCarKit(results);
        model.put("searchingResults", results);
//        model.put("factories", factories);
//        model.put("showrooms", showrooms);
        return new ModelAndView("result", model);
    }

    @RequestMapping(path = "/new_automobile", method = RequestMethod.GET)
    public ModelAndView newAutomobile() {
        Automobile auto = new Automobile();
        Map<String, Object> model = new ModelMap("auto", auto);
        List<CarKit> tempKitsList = searchService.findAllCarKits();
        Set<CarKit> kitsList = new HashSet<>();
        for (CarKit kit : tempKitsList) {
            kitsList.add(kit);
        }
        model.put("kitsList", kitsList);
        return new ModelAndView("new_automobile", model);
    }

    @RequestMapping(path = "/updateDB", method = RequestMethod.POST)
    public View updateDBWithNewAutomobile(@ModelAttribute("auto") Automobile auto, @RequestParam(name = "carKit", required = false) int[] carKitIDs) {
        createService.createAutomobile(auto.getModel(), auto.getMaxPower(), auto.getMaxTorque(), auto.getMaxSpeed(),
                auto.getAcceleration(), auto.getFuelConsumption(), auto.getWeight());
        if (carKitIDs != null) {
            for (int i = 0; i < carKitIDs.length; i++) {
                createService.addCarKitToAutomobile(auto.getModel(), carKitIDs[i]);
            }
        }
        return new RedirectView("/");
    }

    @RequestMapping(path = "/addCarKitToAutomobile", method = RequestMethod.GET)
    public ModelAndView addCarKitToAutomobile(ModelMap model) {
        List<Automobile> autos = searchService.findAllAutomobile();
        List<CarKit> kits = searchService.findAllCarKits();
        Set<CarKit> kitsList = new HashSet<>();
        for (CarKit kit : kits) {
            kitsList.add(kit);
        }
        model.put("autosList", autos);
        model.put("kitsList", kitsList);
        return new ModelAndView("addCarKitToAutomobile", model);
    }

    @RequestMapping(path = "/updateAutomobileWithNewCarKits", method = RequestMethod.POST)
    public View updateAutomobileWithNewCarKits(@RequestParam("modelAuto") String autoModel, @RequestParam(value = "kits", required = false) int[] carKitIDs) {
        try {
            List<CarKit> carKitsForID = new ArrayList<>();
            List<CarKit> carKitsForModel = searchService.findAllCarKitsForAutomobile(autoModel);
            List<CarKit> carKitForAdding = new ArrayList<>();


            for (int i = 0; i < carKitIDs.length; i++) {
                carKitsForID.add(searchService.findCarKitByID(carKitIDs[i]));
            }

            for (CarKit e : carKitsForID) {
                if (!carKitsForModel.contains(e)) {
                    carKitForAdding.add(e);
                }
            }

            if (carKitIDs != null && carKitForAdding.size() != 0) {
                for (int i = 0; i < carKitForAdding.size(); i++) {
                    createService.addCarKitToAutomobile(autoModel, carKitForAdding.get(i).getId());
                }
            }
        } catch (NullPointerException e) {
            return new RedirectView("/");
        }

        return new RedirectView("/");

    }

    @RequestMapping(path = "/new_manufacturingPlant", method = RequestMethod.GET)
    public String newManufacturingPlant(ModelMap model) {
        model.put("factory", new ManufacturingPlant());
        return "new_manufacturingPlant";
    }

    @RequestMapping(path = "/updateDBWithNewManufacturingPlant", method = RequestMethod.POST)
    public View updateDBWithNewManufacturingPlant(@ModelAttribute("factory") ManufacturingPlant factory) {
        if (factory.getCountry() != "")
            createService.createManufacturingPlant(factory.getCountry());
        return new RedirectView("/");
    }

    @RequestMapping(path = "/addManufacturingPlantToAutomobile", method = RequestMethod.GET)
    public String addManufacturingPlantToAutomobile (ModelMap model) {
        List<Automobile> autos = searchService.findAllAutomobile();
        List<ManufacturingPlant> factories = searchService.findAllManufacturingPlant();
        model.put("autosList", autos);
        model.put("manufacturingPlantsList", factories);
        return "addManufacturingPlantToAutomobile";
    }

    @RequestMapping(path = "/updateAutomobileWithNewManufacturingPlant", method = RequestMethod.POST)
    public View updateAutomobileWithNewManufacturingPlant (@RequestParam("modelAuto") String modelAuto, @RequestParam(name = "country", required = false) String[] factoryCountry){
        if (factoryCountry != null) {
            for (int i = 0; i < factoryCountry.length; i++) {
                createService.addPlantToAutomobile(modelAuto, factoryCountry[i]);
            }
        }

        return new RedirectView("/");
    }

    @RequestMapping(path = "/new_CarKit", method = RequestMethod.GET)
    public String newCarKit(ModelMap model) {
        model.put("carKit", new CarKit());
        model.put("autosList", searchService.findAllAutomobile());
        return "new_CarKit";
    }

    @RequestMapping(path = "/updateDBWithNewCarKit" ,method = RequestMethod.POST)
    public View updateDBWithNewCarKit(@ModelAttribute("carKit") CarKit kit) {
        createService.createCarKit(kit);
        return new RedirectView("/");
    }

    @RequestMapping(path = "/addCarKitToCarShowroom", method = RequestMethod.GET)
    public String addCarKitToCarShowroom(ModelMap model) {
        model.put("kitsList", searchService.findAllCarKits());
        model.put("carShowroomList", searchService.findAllCarShowroom());
        return "addCarKitToCarShowroom";
    }

    @RequestMapping(path = "/updateCarKitsWithNewCarShowroom", method = RequestMethod.POST)
    public View updateCarKitsWithNewCarShowroom(@RequestParam(value = "kits", required = false) int[] kitsIDs, @RequestParam(value = "showrooms", required = false) int[] showroomsIDs){
        if (kitsIDs != null && showroomsIDs != null){
            for (int i = 0; i < kitsIDs.length; i++) {
                for (int j = 0; j < showroomsIDs.length; j++) {
                    createService.addCarShowRoomToCarKit(kitsIDs[i], showroomsIDs[j]);
                }
            }
        }
        return new RedirectView("/");
    }

    @RequestMapping(path = "/new_CarShowroom", method = RequestMethod.GET)
    public String newCarShowroom () {
        return "new_CarShowroom";
    }

    @RequestMapping(path = "/updateDBWithNewCarShowroom", method = RequestMethod.POST)
    public  View updateDBWithNewCarShowroom(@RequestParam(value = "nameOfCarShowroom", required = false) String name, @RequestParam(value = "countryOfCarShowroom",required = false) String country,
                                            @RequestParam(value = "cityOfCarShowroom",required = false) String city, @RequestParam(value = "streetOfCarShowroom",required = false) String street){
        if (name!="" && country!="" && city!="" && street!="")
            createService.createCarShowroom(name, country, city, street);
        return new RedirectView("/");
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(CarKit.class, CarKitPropertyEditor);
        binder.registerCustomEditor(Automobile.class, AutomobilePropertyEditor);
    }
}
