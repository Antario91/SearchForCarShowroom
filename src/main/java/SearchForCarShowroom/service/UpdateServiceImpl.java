package SearchForCarShowroom.service;

import SearchForCarShowroom.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Admin on 31.08.2016.
 */
@Service
@Transactional
public class UpdateServiceImpl implements UpdateService {
    @Autowired
    private SearchService searchService;

    @Autowired
    private CreationService creationService;

    @Autowired
    private AutomobileRepo autoRepo;

    @Autowired
    private CarKitRepo kitRepo;

    @Autowired
    private CarShowroomRepo showroomRepo;

    @Autowired
    private ManufacturingPlantRepo factoryRepo;

    @Override
    public void updateAutomobileWithNewCarKits(String autoModel, int[] carKitIDs) throws NullPointerException {
        List<CarKit> carKitsForID = new ArrayList<>();
        List<CarKit> carKitsForModel = searchService.findAllCarKitsForAutomobile(autoModel);
        List<CarKit> carKitForAdding = new ArrayList<>();


        for (int i = 0; i < carKitIDs.length; i++) {
            carKitsForID.add(searchService.findCarKitByID(carKitIDs[i]));
        }

        for (CarKit e : carKitsForID) {
            if (!carKitsForModel.contains(e)) {
                carKitForAdding.add(e);
            }
        }

        if (carKitForAdding.size() != 0) {
            for (int i = 0; i < carKitForAdding.size(); i++) {
                addCarKitToAutomobile(autoModel, carKitForAdding.get(i).getId());
            }
        }
    }

    @Override
    public void createAutomobileAndAddCarKit(Automobile auto, int[] carKitIDs) {
        creationService.createAutomobile(auto.getModel(), auto.getMaxPower(), auto.getMaxTorque(), auto.getMaxSpeed(),
                auto.getAcceleration(), auto.getFuelConsumption(), auto.getWeight());
        if (carKitIDs != null) {
            for (int i = 0; i < carKitIDs.length; i++) {
                addCarKitToAutomobile(auto.getModel(), carKitIDs[i]);
            }
        }
    }

    @Override
    public void addPlantToAutomobile(String autoModel, String factoryCountry) {
        Automobile automobile = autoRepo.getByModel(autoModel);
        ManufacturingPlant manufacturingPlant = factoryRepo.getByCountry(factoryCountry);
        automobile.addManufacturingPlant(manufacturingPlant);
//        System.out.println("addPlantToAutomobile +1");
    }

    @Override
    public void removePlantFromAutomobile(String autoModel, String factoryCountry) {
        Automobile automobile = autoRepo.getByModel(autoModel);
        ManufacturingPlant manufacturingPlant = factoryRepo.getByCountry(factoryCountry);
        automobile.removeManufacturingPlant(manufacturingPlant);
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

    @Override
    public void removeCarKitFromAutomobile(String model, int carKitId) {
        Automobile auto = autoRepo.getByModel(model);
        auto.getCarKit().remove(kitRepo.getById(carKitId));
        autoRepo.add(auto);
    }

    @Override
    public void updateAutomobileInDB (Automobile modifyingAuto, int idAutoInDB,
                                      int[] newCarKitsIDs, int[] currentCarKitsIDs,
                                      String[] newFactoriesCountries, String[] currentFactoriesCountries,
                                      String isDeleteAutomobile) {
        try {
            if (isDeleteAutomobile == null) {
                isDeleteAutomobile = "false";
            }
        }catch (NullPointerException e){
            isDeleteAutomobile = "false";
        }finally {
            if (isDeleteAutomobile.equals("true")) {
                autoRepo.delete(autoRepo.getById(idAutoInDB));
                return;
            }

            Automobile automobileInDB = autoRepo.getById(idAutoInDB);
            if (!modifyingAuto.equals(automobileInDB)) {
                automobileInDB.ifNotEqualsSetProperies(modifyingAuto);
                creationService.createAutomobile(automobileInDB);
            }

            if (newCarKitsIDs != null){
                for (int i = 0; i < newCarKitsIDs.length; i++) {
                    addCarKitToAutomobile(modifyingAuto.getModel(), newCarKitsIDs[i]);
                }
            }

            if (currentCarKitsIDs != null){
                for (int i = 0; i < currentCarKitsIDs.length; i++) {
                    removeCarKitFromAutomobile(modifyingAuto.getModel(), currentCarKitsIDs[i]);
                }
            }

            if (newFactoriesCountries != null){
                for (int i = 0; i < newFactoriesCountries.length; i++) {
                    addPlantToAutomobile(modifyingAuto.getModel(), newFactoriesCountries[i]);
                }
            }

            if (currentFactoriesCountries != null){
                for (int i = 0; i < currentFactoriesCountries.length; i++) {
                    removePlantFromAutomobile(modifyingAuto.getModel(), currentFactoriesCountries[i]);
                }
            }
        }
    }
}
