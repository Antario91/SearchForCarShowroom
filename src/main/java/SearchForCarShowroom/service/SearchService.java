package SearchForCarShowroom.service;

import SearchForCarShowroom.domain.Automobile;
import SearchForCarShowroom.domain.CarKit;
import SearchForCarShowroom.domain.CarShowroom;
import SearchForCarShowroom.domain.ManufacturingPlant;

import java.util.List;
import java.util.Map;

/**
 * Created by AlexandrGoloborodko on 24.08.16.
 */
public interface SearchService {
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
    Map<String,Object> getDataForResults(String autoModel, Map<String, Integer> price, String description);
    Map<String,Object> getAllAutomobileAndAllCarKits();
    Map<String,Object> getAllAutomobileAndAllManufacturingPlant();
    Map<String,Object> getAllCarKitsAndAllCarShowrooms();
    Map<Integer, List<ManufacturingPlant>> findFactoriesOfAutomobile(List<CarKit> carKits);
    Map<Integer, List<CarShowroom>> findCarShowroomOfCarKit(List<CarKit> carKits);
    Map<String,Object> getDataForModifyAutomobile(int id);
}
