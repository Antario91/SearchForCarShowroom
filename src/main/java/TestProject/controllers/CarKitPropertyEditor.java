package TestProject.controllers;

import TestProject.service.searchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;

/**
 * Created by AlexandrGoloborodko on 25.08.16.
 */
@Component
@Qualifier("CarKitPropertyEditor")
public class CarKitPropertyEditor extends PropertyEditorSupport {
    @Autowired
    private searchService service;

    @Override
    public void setAsText(String text) {
        setValue(service.findCarKitByID(Integer.parseInt(text)));
    }
}
