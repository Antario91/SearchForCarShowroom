package TestProject.service;

import TestProject.domain.*;
import TestProject.repository.AutomobileRepo;
import TestProject.repository.CarKitRepo;
import TestProject.repository.CarShowroomRepo;
import TestProject.repository.ManufacturingPlantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by AlexandrGoloborodko on 24.08.16.
 */
@Service
@Transactional
public class searchServiceImpl implements searchService{
    @Autowired
    private AutomobileRepo autoRepo;

    @Autowired
    private CarKitRepo kitRepo;

    @Autowired
    private CarShowroomRepo showroomRepo;

    @Autowired
    private ManufacturingPlantRepo factoryRepo;

    @Override
    public List<String> getAllAutomobileModels () {
        List<String> models = new ArrayList<>();
        List<Automobile> autos = findAllAutomobile();
        for (Automobile auto: autos) {
            models.add(auto.getModel());
        }
        return models;
    }

    @Override
    public List<Automobile> findAllAutomobile() {
        return autoRepo.getAllEntity();
    }

    @Override
    public List<CarKit> findAllCarKits(){
        return kitRepo.getAllEntity();
    }

    @Override
    public List<CarShowroom> findAllCarShowroom() {
        return showroomRepo.getAllEntity();
    }

    @Override
    public CarKit findCarKitByID(int id) {
        return kitRepo.getById(id);
    }

    @Override
    public Automobile findAutomobileByModel(String model) {
        return autoRepo.getByModel(model);
    }

    @Override
    public ManufacturingPlant findManufacturingPlantByCountry(String country) {
        return factoryRepo.getByCountry(country);
    }

    @Override
    public List<ManufacturingPlant> findAllManufacturingPlant() {
        return factoryRepo.getAllEntity();
    }

    @Override
    public List<CarKit> findAllCarKitsForAutomobile(String model){
        Iterator<CarKit> itr = autoRepo.getByModel(model).getCarKit().iterator();
        while (itr.hasNext()){
            CarKit temp = itr.next();
            System.out.println(temp);
            System.out.println();
        }
        return autoRepo.getByModel(model).getCarKit();
    }

    @Override
    public List<CarKit> findByCostAndDescription(Map<String, Integer> processedPrice, String description){
        return kitRepo.getByCostAndDescription(processedPrice, description);
    }

    @Override
    public List<CarKit> findByAutoModelAndDescription(String model, String description) {
        Automobile auto = autoRepo.getByModel(model);

        List<CarKit> carKits = auto.getCarKit();

        Iterator<CarKit> itr = carKits.iterator();


        List<CarKit> temp = new ArrayList<>();
        while (itr.hasNext()) {
            CarKit kit = itr.next();
            if (kit.getDescription().equals(description))
                temp.add(kit);
        }
        return temp;
    }

    @Override
    public List<CarKit> findByModelAndCostAndDescription(String model, Map<String, Integer> processedPrice, String description) {
//        int autoId = autoRepo.getByModel(model).getId();
        List<CarKit> kit = kitRepo.getByCostAndDescription(processedPrice, description);
        List<CarKit> temp = new ArrayList<>();
        for (int i = 0; i < kit.size(); i++) {
            if (kit.get(i).getAuto().getModel().equals(model))
                temp.add(kit.get(i));
        }
        return temp;
    }

    @Override
    public Map<Integer, List<ManufacturingPlant>> findFactoriesOfAutomobile(List<CarKit> tempCarKits) {
        List<CarKit> carKits = new ArrayList<>();
        for(CarKit kit:tempCarKits){
            carKits.add(kitRepo.getById(kit.getId()));
        }

        Map<Integer, List<ManufacturingPlant>> temp = new HashMap<>();
        List<ManufacturingPlant> factories = new ArrayList<>();

        for(CarKit kit:carKits){
            List<AutomobileManufacturingPlantAdditionalTable> autofactory= kit.getAuto().getAutofactory();


            for (AutomobileManufacturingPlantAdditionalTable additionalTable:autofactory){
                factories.add(additionalTable.getFactory());
            }

            temp.put(kit.getId(), factories);
            factories = new ArrayList<>();
        }
        return temp;
    }

    @Override
    public Map<Integer, List<CarShowroom>> findCarShowroomOfCarKit(List<CarKit> tempCarKits) {
        List<CarKit> carKits = new ArrayList<>();
        for(CarKit kit:tempCarKits){
            carKits.add(kitRepo.getById(kit.getId()));
        }

        Map<Integer, List<CarShowroom>> temp = new HashMap<>();
        List<CarShowroom> showrooms = new ArrayList<>();

        for(CarKit kit:carKits){
            List<CarKitCarShowroomAdditionalTable> kitShowrooms= kit.getKitShowrooms();


            for (CarKitCarShowroomAdditionalTable additionalTable:kitShowrooms){
                showrooms.add(additionalTable.getShowroom());
            }

            temp.put(kit.getId(), showrooms);
            showrooms = new ArrayList<>();
        }
        return temp;
    }
}