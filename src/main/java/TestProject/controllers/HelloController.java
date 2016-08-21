package TestProject.controllers;

import TestProject.DBInitializer.DBInitialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by AlexandrGoloborodko on 21.08.16.
 */
@Controller
public class HelloController {

    @Autowired
    DBInitialize initializer;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String index(){
        initializer.initDB();

        return "index";
    }
}
