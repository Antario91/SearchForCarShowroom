package TestProject.controllers;

import TestProject.DBInitializer.DBInitialize;
import TestProject.domain.Automobile;
import TestProject.service.creationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by AlexandrGoloborodko on 21.08.16.
 */
@Controller
public class HelloController {

    @Autowired
    DBInitialize initializer;

    @Autowired
    creationService service;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ModelAndView index(){
        initializer.initDB();

        Automobile auto = service.findAutomobileByModel("Rapid");

        ModelAndView modelAndView = new ModelAndView("index","car",auto);

        return modelAndView;
    }
}
