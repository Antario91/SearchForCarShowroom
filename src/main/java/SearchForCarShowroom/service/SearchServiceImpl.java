package SearchForCarShowroom.service;

import SearchForCarShowroom.domain.*;
import SearchForCarShowroom.domain.AutomobileRepo;
import SearchForCarShowroom.domain.CarKitRepo;
import SearchForCarShowroom.domain.CarShowroomRepo;
import SearchForCarShowroom.domain.ManufacturingPlantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by AlexandrGoloborodko on 24.08.16.
 */
@Service
@Transactional
public class SearchServiceImpl implements SearchService {
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
//        Iterator<CarKit> itr = autoRepo.getByModel(model).getCarKit().iterator();
//        while (itr.hasNext()){
//            CarKit temp = itr.next();
//            System.out.println(temp);
//            System.out.println();
//        }
        return autoRepo.getByModel(model).getCarKit();
    }

    @Override
    public List<CarKit> findByCostAndDescription(Map<String, Integer> processedPrice, String description){
        return kitRepo.getByCostAndDescription(processedPrice, description);
    }

    @Override
    public List<CarKit> findByAutoModelAndDescription(String model, String description) {
        List<CarKit> kits = kitRepo.getByDescription(description);
        List<CarKit> temp = new ArrayList<>();
        for (int i = 0; i < kits.size(); i++) {
            if (kits.get(i).getAuto().getModel().equals(model))
                temp.add(kits.get(i));
        }
        return temp;
    }

    @Override
    public List<CarKit> findByModelAndCostAndDescription(String model, Map<String, Integer> processedPrice, String description) {
        List<CarKit> kit = kitRepo.getByCostAndDescription(processedPrice, description);
        List<CarKit> temp = new ArrayList<>();
        for (int i = 0; i < kit.size(); i++) {
            if (kit.get(i).getAuto().getModel().equals(model))
                temp.add(kit.get(i));
        }
        return temp;
    }

    @Override
    public Map<String,Object> getDataForResults(String autoModel, Map<String, Integer> price, String description) {
        Map<String,Object> temp = new HashMap<>();
        List<CarKit> results = null;
        Map<Integer, List<ManufacturingPlant>> factories = null;
        Map<Integer, List<CarShowroom>> showrooms = null;

        if (autoModel.equals("null")){
            results = findByCostAndDescription(price, description);
        } else if (!autoModel.equals("null") && price.get("minCost") == 0 && price.get("maxCost") == 1000000000){
            results = findByAutoModelAndDescription(autoModel, description);
        } else {
            results = findByModelAndCostAndDescription(autoModel, price, description);
        }

        factories = findFactoriesOfAutomobile(results);
        showrooms = findCarShowroomOfCarKit(results);

        temp.put("results", results);
        temp.put("factories", factories);
        temp.put("showrooms", showrooms);
        return temp;
    }

    @Override
    public Map<String,Object> getAllAutomobileAndAllCarKits() {
        Map<String,Object> temp = new HashMap<>();
        temp.put("autos", findAllAutomobile());
        temp.put("kits", findAllCarKits());
        return temp;
    }

    @Override
    public Map<String,Object> getAllAutomobileAndAllManufacturingPlant() {
        Map<String,Object> temp = new HashMap<>();
        temp.put("autosList", findAllAutomobile());
        temp.put("manufacturingPlantsList", findAllManufacturingPlant());
        return temp;
    }

    @Override
    public Map<String,Object> getAllCarKitsAndAllCarShowrooms() {
        Map<String,Object> temp = new HashMap<>();
        temp.put("kitsList", findAllCarKits());
        temp.put("carShowroomList", findAllCarShowroom());
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

    @Override
    public Map<String,Object> getDataForModifyAutomobile(int id) {
        Map<String, Object> temp = new HashMap<>();
        Automobile auto = autoRepo.getById(id);
        temp.put("auto", auto);

        List<CarKit> currentCarKits = auto.getCarKit();
        temp.put("current_carKits", currentCarKits);
        List<CarKit> newCarKits = findAllCarKits();
        newCarKits.removeAll(currentCarKits);
        temp.put("new_carKits", newCarKits);

        List<AutomobileManufacturingPlantAdditionalTable> autofactory = auto.getAutofactory();
        List<ManufacturingPlant> currentFactories = new ArrayList<>();
        for (AutomobileManufacturingPlantAdditionalTable e:autofactory){
            currentFactories.add(e.getFactory());
        }
        temp.put("current_factories", currentFactories);

        List<ManufacturingPlant> newFactories = findAllManufacturingPlant();
        newFactories.removeAll(currentFactories);
        temp.put("new_factories", newFactories);

        return temp;
    }
}