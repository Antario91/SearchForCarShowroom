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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

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


    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String index(){
        initializer.initDB();

        return "index";
    }

    @RequestMapping(path = "/form", method = RequestMethod.GET)
    public ModelAndView form(){
        searchObject search = new searchObject();
        ModelAndView modelAndView = new ModelAndView("form","searchObject",search);
        return modelAndView;
    }

    @RequestMapping(path = "/result", method = RequestMethod.POST)
    public ModelAndView result(@ModelAttribute("searchObject") searchObject search){
        Map<String, Object> model = new ModelMap("searchObject", search);

        Map<String, Integer> price = search.pricesHandler();
        List<CarKit> results = null;

        if (search.getModel().equals("null")) {
            results = searchService.findByCostAndDescription(price, search.getDescription());
            model.put("searchingResults", results);
            return new ModelAndView("result", model);
        }

        if (search.getModel().equals("null") && price.get("minCost") == 0 && price.get("maxCost") == 1000000000) {
            results = searchService.findByAutoModelAndDescription(search.getModel(), search.getDescription());
            model.put("searchingResults", results);
            return new ModelAndView("result", model);
        }

        results = searchService.findByModelAndCostAndDescription(search.getModel(), price, search.getDescription());
        model.put("searchingResults", results);
        return new ModelAndView("result", model);
    }

    @RequestMapping(path = "/new_automobile", method = RequestMethod.GET)
    public ModelAndView newAutomobile(){
        Automobile auto = new Automobile();
        Map<String, Object> model = new ModelMap("auto", auto);
        List<CarKit> kitsList = searchService.findAllCarKits();
        model.put("kitsList", kitsList);
        return new ModelAndView("new_automobile", model);
    }

    @RequestMapping(path = "/updateDB", method = RequestMethod.POST)
    public String updateDBWithNewAutomobile(@ModelAttribute("auto") Automobile auto){
        createService.createAutomobile(auto.getModel(),auto.getMaxPower(),auto.getMaxTorque(),auto.getMaxSpeed(),
                                        auto.getAcceleration(),auto.getFuelConsumption(),auto.getWeight());
        return "index";
    }
}
