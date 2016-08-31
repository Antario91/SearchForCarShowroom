package SearchForCarShowroom.controllers;

import SearchForCarShowroom.DBInitializer.DBInitialize;
import SearchForCarShowroom.domain.Automobile;
import SearchForCarShowroom.domain.CarKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.beans.PropertyEditor;

/**
 * Created by Admin on 31.08.2016.
 */
@Controller
public class GreetingController {
    @Autowired
    private DBInitialize initializer;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String index() {
        initializer.initDB();
        return "index";
    }
}
