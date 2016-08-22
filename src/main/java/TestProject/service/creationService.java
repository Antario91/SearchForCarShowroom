package TestProject.service;

import TestProject.domain.Automobile;
import TestProject.domain.CarKit;
import TestProject.domain.CarShowroom;
import TestProject.domain.ManufacturingPlant;

import java.util.List;
import java.util.Set;

/**
 * Created by AlexandrGoloborodko on 20.08.16.
 */
public interface creationService {
    Automobile createAutomobile (String model, double maxPower, double maxTorque, double maxSpeed, double acceleration,
                                 double fuelConsumption, double weight);
    CarKit createCarKit (String automobileModel, boolean windowTinting, boolean alloyWheels, boolean immobiliser, boolean radioEquipment,
                         boolean cruiseControl, int cost);
    CarShowroom createCarShowroom (String name, String country, String city, String street);
    ManufacturingPlant createManufacturingPlant (String country);
    List<Automobile> findAllAutomobile ();
    Automobile findAutomobileByModel(String model);
    ManufacturingPlant findManufacturingPlantByCountry (String country);
    List<CarKit> findAllCarKits();
    Set<CarKit> findAllCarKitsForAutomobile(String model);
    void addPlantToAutomobile (String autoModel, String factoryCountry);
    void addCarShowRoomToCarKitByCost (int minCostCarKit, int maxCostCarKit, String nameOfShowroom);
//    List<?> checkingDataBase ();
}
