package SearchForCarShowroom.controllers;

import SearchForCarShowroom.domain.CarKit;
import SearchForCarShowroom.domain.CarShowroom;
import SearchForCarShowroom.domain.ManufacturingPlant;
import SearchForCarShowroom.resultOfSearch.SearchObject;
import SearchForCarShowroom.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 31.08.2016.
 */
@Controller
public class SearchController {
    @Autowired
    private SearchService searchService;

    @RequestMapping(path = "/search", method = RequestMethod.GET)
    public ModelAndView search() {
        SearchObject search = new SearchObject();
        List<String> automobileModels = searchService.getAllAutomobileModels();
        ModelMap model = new ModelMap("SearchObject", search);
        model.put("modelList", automobileModels);
        ModelAndView modelAndView = new ModelAndView("search", model);
        return modelAndView;
    }

    @RequestMapping(path = "/result", method = RequestMethod.POST)
    public ModelAndView result(@ModelAttribute("SearchObject") SearchObject search, ModelMap model) {
        Map<String, Integer> price = search.pricesHandler();

        Map<String, Object> dataForResults = searchService.getDataForResults(search.getModel(), price, search.getDescription());

        model.put("searchingResults", (List<CarKit>) dataForResults.get("results"));
        model.put("factories", (Map<Integer, List<ManufacturingPlant>>) dataForResults.get("factories"));
        model.put("showrooms", (Map<Integer, List<CarShowroom>>) dataForResults.get("showrooms"));

//        model = searchService.getDataForResults(search.getModel(), price, search.getDescription());
        return new ModelAndView("result", model);
    }

    @RequestMapping(path = "/image/automobile/{id}", method = RequestMethod.GET)
    public void showImage (@PathVariable("id") int id, HttpServletResponse response) throws IOException {
        response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
        response.getOutputStream().write(searchService.findAutomobileByID(id).getImage());
        response.getOutputStream().close();
    }
}
