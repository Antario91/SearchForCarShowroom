package TestProject.service;

import TestProject.domain.Automobile;
import TestProject.domain.CarKit;
import TestProject.domain.ManufacturingPlant;
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

    public List<CarKit> findByCostAndDescription(Map<String, Integer> processedPrice, String description){
        return kitRepo.getByCostAndDescription(processedPrice, description);
    }

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

    public List<CarKit> findByModelAndCostAndDescription(String model, Map<String, Integer> processedPrice, String description) {
//        int autoId = autoRepo.getByModel(model).getId();
        List<CarKit> kit = kitRepo.getByCostAndDescription(processedPrice, description);
        List<CarKit> temp = new ArrayList<>();
        for (int i = 0; i < kit.size(); i++) {
            if (kit.get(i).getAutomobile().getModel().equals(model))
                temp.add(kit.get(i));
        }
        return temp;

    }
}
