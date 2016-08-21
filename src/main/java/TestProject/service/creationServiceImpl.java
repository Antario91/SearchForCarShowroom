package TestProject.service;

import TestProject.domain.*;
import TestProject.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by AlexandrGoloborodko on 21.08.16.
 */
@Service
@Transactional
public class creationServiceImpl implements creationService {

    @Autowired
    private AutomobileRepo autoRepo;

    @Autowired
    private CarKitRepo kitRepo;

    @Autowired
    private CarShowroomRepo showroomRepo;

    @Autowired
    private ManufacturingPlantRepo factoryRepo;

//    @Autowired
//    private GenericRepo<?> genericRepo;

    @Override
    public Automobile createAutomobile(String model, double maxPower, double maxTorque, double maxSpeed,
                                       double acceleration, double fuelConsumption, double weight) {
        Automobile auto = new Automobile(model, maxPower, maxTorque, maxSpeed, acceleration, fuelConsumption, weight);
        autoRepo.add(auto);
        return auto;
    }

    @Override
    public CarKit createCarKit(String automobileModel, boolean windowTinting, boolean alloyWheels, boolean immobiliser, boolean radioEquipment,
                               boolean cruiseControl, int cost) {
        Automobile auto = autoRepo.getByModel(automobileModel);
        CarKit kit = new CarKit(windowTinting, alloyWheels, immobiliser, radioEquipment, cruiseControl, cost);
        kit.setAutomobile(auto);
        kitRepo.add(kit);
//        auto.getCarKit().add(kit);
        return kit;
    }

    @Override
    public CarShowroom createCarShowroom(String name, String country, String city, String street) {
        Address address = new Address(country, city, street);
        CarShowroom showroom = new CarShowroom(name, address);
        showroomRepo.add(showroom);
        return showroom;
    }

    @Override
    public ManufacturingPlant createManufacturingPlant(String country) {
        ManufacturingPlant factory = new ManufacturingPlant(country);
        factoryRepo.add(factory);
        return factory;
    }

    @Override
    public List<Automobile> findAllAutomobile() {
        return autoRepo.getAllEntity();
    }

    public List<CarKit> findAllCarKits(){
        return kitRepo.getAllEntity();
    }

    @Override
    public Automobile findAutomobileByModel(String model) {
        return autoRepo.getByModel(model);
    }

    @Override
    public ManufacturingPlant findManufacturingPlantByCountry(String country) {
        return factoryRepo.getByCountry(country);
    }

    public Set<CarKit> findAllCarKitsForAutomobile(String model){
        Iterator<CarKit> itr = autoRepo.getByModel(model).getCarKit().iterator();
        while (itr.hasNext()){
            CarKit temp = itr.next();
            System.out.println(temp);
            System.out.println();
        }
        return autoRepo.getByModel(model).getCarKit();
    }

    @Override
    public void addPlantToAutomobile(Automobile auto, ManufacturingPlant factory) {
        auto.addManufacturingPlant(factory);
    }

    @Override
    public void addCarShowRoomToCarKit(CarKit kit, CarShowroom showroom) {
        kit.addCarShowroom(showroom);
    }


//    @Override
//    public List<?> checkingDataBase() {
//        return genericRepo.checkingDataBases();
//    }


}
