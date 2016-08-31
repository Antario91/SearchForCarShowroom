package SearchForCarShowroom.controllers;

import SearchForCarShowroom.domain.Automobile;
import SearchForCarShowroom.domain.CarKit;
import SearchForCarShowroom.domain.ManufacturingPlant;
import SearchForCarShowroom.service.CreationService;
import SearchForCarShowroom.service.SearchService;
import SearchForCarShowroom.service.UpdateService;
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
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Admin on 31.08.2016.
 */
@Controller
public class CreationController {
    @Autowired
    private CreationService creationService;

    @Autowired
    private SearchService searchService;

    @Autowired
    private UpdateService updateService;

    @Autowired
    @Qualifier("CarKitPropertyEditor")
    private PropertyEditor CarKitPropertyEditor;

    @Autowired
    @Qualifier("AutomobilePropertyEditor")
    private PropertyEditor AutomobilePropertyEditor;

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

    @RequestMapping(path = "/updateDBWithNewAutomobile", method = RequestMethod.GET)
    public View updateDBWithNewAutomobile(@ModelAttribute("auto") Automobile auto, @RequestParam(name = "carKit", required = false) int[] carKitIDs) {
        updateService.createAutomobileAndAddCarKit(auto, carKitIDs);
        return new RedirectView("/");
    }


    @RequestMapping(path = "/new_carKit", method = RequestMethod.GET)
    public String newCarKit(ModelMap model) {
        model.put("carKit", new CarKit());
        model.put("autosList", searchService.findAllAutomobile());
        return "new_carKit";
    }

    @RequestMapping(path = "/updateDBWithNewCarKit", method = RequestMethod.POST)
    public View updateDBWithNewCarKit(@ModelAttribute("carKit") CarKit kit) {
        creationService.createCarKit(kit);
        return new RedirectView("/");
    }


    @RequestMapping(path = "/new_manufacturingPlant", method = RequestMethod.GET)
    public String newManufacturingPlant(ModelMap model) {
        model.put("factory", new ManufacturingPlant());
        return "new_manufacturingPlant";
    }

    @RequestMapping(path = "/updateDBWithNewManufacturingPlant", method = RequestMethod.POST)
    public View updateDBWithNewManufacturingPlant(@ModelAttribute("factory") ManufacturingPlant factory) {
        if (factory.getCountry().equals(""))
            creationService.createManufacturingPlant(factory);
        return new RedirectView("/");
    }


    @RequestMapping(path = "/new_carShowroom", method = RequestMethod.GET)
    public String newCarShowroom() {
        return "new_carShowroom";
    }

    @RequestMapping(path = "/updateDBWithNewCarShowroom", method = RequestMethod.POST)
    public View updateDBWithNewCarShowroom(@RequestParam(value = "nameOfCarShowroom", required = false) String name, @RequestParam(value = "countryOfCarShowroom", required = false) String country,
                                           @RequestParam(value = "cityOfCarShowroom", required = false) String city, @RequestParam(value = "streetOfCarShowroom", required = false) String street) {
        if (!name.equals("") && !country.equals("") && !city.equals("") && !street.equals(""))
            creationService.createCarShowroom(name, country, city, street);
        return new RedirectView("/");
    }


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(CarKit.class, CarKitPropertyEditor);
        binder.registerCustomEditor(Automobile.class, AutomobilePropertyEditor);
    }
}
