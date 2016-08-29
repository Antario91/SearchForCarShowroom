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

    @Override
    public Automobile createAutomobile (Automobile auto) {
        autoRepo.add(auto);
        return auto;
    }

    @Override
    public Automobile createAutomobile(String model, double maxPower, double maxTorque, double maxSpeed,
                                       double acceleration, double fuelConsumption, double weight) {
        Automobile auto = new Automobile(model, maxPower, maxTorque, maxSpeed, acceleration, fuelConsumption, weight);
        autoRepo.add(auto);
        return auto;
    }

    @Override
    public CarKit createCarKit (CarKit kit) {
        kitRepo.add(kit);
        return kit;
    }

    @Override
    public CarKit createCarKit(String automobileModel, boolean windowTinting, boolean alloyWheels, boolean immobiliser, boolean radioEquipment,
                               boolean cruiseControl, int cost) {
        Automobile auto = autoRepo.getByModel(automobileModel);
        CarKit kit = new CarKit(windowTinting, alloyWheels, immobiliser, radioEquipment, cruiseControl, cost);
        kit.setAuto(auto);
        kitRepo.add(kit);
//        auto.getCarKit().add(kit);
        return kit;
    }

    @Override
    public CarShowroom createCarShowroom (CarShowroom showroom) {
        showroomRepo.add(showroom);
        return showroom;
    }

    @Override
    public CarShowroom createCarShowroom(String name, String country, String city, String street) {
        Address address = new Address(country, city, street);
        CarShowroom showroom = new CarShowroom(name, address);
        showroomRepo.add(showroom);
        return showroom;
    }

    @Override
    public ManufacturingPlant createManufacturingPlant (ManufacturingPlant factory){
        factoryRepo.add(factory);
        return factory;
    }

    @Override
    public ManufacturingPlant createManufacturingPlant(String country) {
        ManufacturingPlant factory = new ManufacturingPlant(country);
        factoryRepo.add(factory);
        return factory;
    }

    @Override
    public void addPlantToAutomobile(String autoModel, String factoryCountry) {
        Automobile automobile = autoRepo.getByModel(autoModel);
        ManufacturingPlant manufacturingPlant = factoryRepo.getByCountry(factoryCountry);
        automobile.addManufacturingPlant(manufacturingPlant);
        System.out.println("addPlantToAutomobile +1");
    }

    @Override
    public void addCarShowRoomToCarKitByCost(int minCostCarKit, int maxCostCarKit, String nameOfShowroom) {
        CarShowroom showroom = showroomRepo.getByName(nameOfShowroom);
        List<CarKit> kits = kitRepo.getByCost(minCostCarKit, maxCostCarKit);
        Iterator<CarKit> itr = kits.iterator();
        CarKit tempCarKit = null;
        while (itr.hasNext()) {
            tempCarKit = itr.next();
            if (tempCarKit.getCost()>=minCostCarKit || tempCarKit.getCost()<=maxCostCarKit) {
                tempCarKit.addCarShowroom(showroom);
            }
        }
    }

    @Override
    public void addCarShowRoomToCarKit(int CarKitID, int CarShowroomID) {
        CarKit kit = kitRepo.getById(CarKitID);
        CarShowroom showroom = showroomRepo.getById(CarShowroomID);
        kit.addCarShowroom(showroom);
    }

    @Override
    public void addCarKitToAutomobile (String model, int carKitId) {
        Automobile auto = autoRepo.getByModel(model);
        CarKit kit = kitRepo.getById(carKitId);
        CarKit temp = new CarKit(kit.isWindowTinting(),kit.isAlloyWheels(),kit.isImmobiliser(),kit.isRadioEquipment(),
                                    kit.isCruiseControl(),kit.getCost());
        temp.setAuto(auto);
        kitRepo.add(temp);
    }
}
