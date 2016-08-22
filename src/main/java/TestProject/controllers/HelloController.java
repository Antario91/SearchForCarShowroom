package TestProject.controllers;

import TestProject.DBInitializer.DBInitialize;
import TestProject.domain.Automobile;
import TestProject.resultOfSearch.searchObject;
import TestProject.service.creationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by AlexandrGoloborodko on 21.08.16.
 */
@Controller
public class HelloController {

    @Autowired
    private DBInitialize initializer;

    @Autowired
    private creationService service;


    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ModelAndView index(){
        initializer.initDB();

        Automobile auto = service.findAutomobileByModel("Rapid");

        ModelAndView modelAndView = new ModelAndView("index","car",auto);

        return modelAndView;
    }

    @RequestMapping(path = "/form", method = RequestMethod.GET)
    public ModelAndView form(){
        searchObject search = new searchObject();
        ModelAndView modelAndView = new ModelAndView("form","searchObject",search);
        return modelAndView;
    }

    @RequestMapping(path = "/result", method = RequestMethod.POST)
    public ModelAndView result(@ModelAttribute("searchObject") searchObject search){
        return new ModelAndView("result", "searchObject", search);

    }
}
