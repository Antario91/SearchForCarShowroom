package TestProject.service;

import TestProject.domain.Automobile;
import TestProject.domain.CarKit;
import TestProject.domain.CarShowroom;
import TestProject.domain.ManufacturingPlant;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by AlexandrGoloborodko on 24.08.16.
 */
public interface searchService {
    List<String> getAllAutomobileModels ();
    List<Automobile> findAllAutomobile();
    List<CarKit> findAllCarKits();
    List<CarShowroom> findAllCarShowroom();
    CarKit findCarKitByID(int id);
    Automobile findAutomobileByModel(String model);
    ManufacturingPlant findManufacturingPlantByCountry(String country);
    List<ManufacturingPlant> findAllManufacturingPlant();
    List<CarKit> findAllCarKitsForAutomobile(String model);
    List<CarKit> findByCostAndDescription(Map<String, Integer> processedPrice, String description);
    List<CarKit> findByAutoModelAndDescription(String model, String description);
    List<CarKit> findByModelAndCostAndDescription(String model, Map<String, Integer> processedPrice, String description);
    Map<Integer, List<ManufacturingPlant>> findFactoriesOfAutomobile(List<CarKit> carKits);
    Map<Integer, List<CarShowroom>> findCarShowroomOfCarKit(List<CarKit> carKits);

}
