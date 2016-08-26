package TestProject.controllers;

import TestProject.DBInitializer.DBInitialize;
import TestProject.domain.Automobile;
import TestProject.domain.CarKit;
import TestProject.resultOfSearch.searchObject;
import TestProject.service.creationService;
import TestProject.service.searchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import java.beans.PropertyEditor;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    private PropertyEditor editor;


    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String index(){
        initializer.initDB();

        return "index";
    }

    @RequestMapping(path = "/form", method = RequestMethod.GET)
    public ModelAndView form(){
        searchObject search = new searchObject();
        List<String> automobileModels = searchService.getAllAutomobileModels();
        ModelMap model = new ModelMap("searchObject", search);
        model.put("modelList", automobileModels);
        ModelAndView modelAndView = new ModelAndView("form",model);
        return modelAndView;
    }

    @RequestMapping(path = "/result", method = RequestMethod.POST)
    public ModelAndView result(@ModelAttribute("searchObject") searchObject search){
        Map<String, Integer> price = search.pricesHandler();
        List<CarKit> results = null;

        if (search.getModel().equals("null")) {
            results = searchService.findByCostAndDescription(price, search.getDescription());
            return new ModelAndView("result", "searchingResults", results);
        }

        if (search.getModel().equals("null") && price.get("minCost") == 0 && price.get("maxCost") == 1000000000) {
            results = searchService.findByAutoModelAndDescription(search.getModel(), search.getDescription());
            return new ModelAndView("result", "searchingResults", results);
        }

        results = searchService.findByModelAndCostAndDescription(search.getModel(), price, search.getDescription());
        return new ModelAndView("result", "searchingResults", results);
    }

    @RequestMapping(path = "/new_automobile", method = RequestMethod.GET)
    public ModelAndView newAutomobile(){
        Automobile auto = new Automobile();
        Map<String, Object> model = new ModelMap("auto", auto);
        List<CarKit> tempKitsList = searchService.findAllCarKits();
        Set<CarKit> kitsList = new HashSet<>();
        for(CarKit kit:tempKitsList){
            kitsList.add(kit);
        }
        model.put("kitsList", kitsList);
        return new ModelAndView("new_automobile", model);
    }

    @RequestMapping(path = "/updateDB", method = RequestMethod.GET)
    public View updateDBWithNewAutomobile(@ModelAttribute("auto") Automobile auto, @RequestParam("carKit") int[] carKitIDs){
        createService.createAutomobile(auto.getModel(),auto.getMaxPower(),auto.getMaxTorque(),auto.getMaxSpeed(),
                                        auto.getAcceleration(),auto.getFuelConsumption(),auto.getWeight());
        for (int i = 0; i < carKitIDs.length; i++) {
            createService.addCarKitToAutomobile(auto.getModel(), carKitIDs[i]);
        }
        return new RedirectView("/");
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(CarKit.class, editor);
    }
}
