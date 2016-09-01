package SearchForCarShowroom.service;

import SearchForCarShowroom.domain.Automobile;

/**
 * Created by Admin on 31.08.2016.
 */
public interface UpdateService {
    void updateAutomobileWithNewCarKits(String autoModel, int[] carKitIDs);
    void createAutomobileAndAddCarKit(Automobile auto, int[] carKitIDs);
    void addPlantToAutomobile(String autoModel, String factoryCountry);
    void removePlantFromAutomobile(String autoModel, String factoryCountry);
    void addCarShowRoomToCarKitByCost(int minCostCarKit, int maxCostCarKit, String nameOfShowroom);
    void addCarShowRoomToCarKit(int CarKitID, int CarShowroomID);
    void addCarKitToAutomobile (String model, int carKitId);
    void removeCarKitFromAutomobile(String model, int carKitId);
    void updateAutomobileInDB (Automobile newAuto, Automobile autoFromModelAttribute,
                               int[] newCarKitsIDs, int[] currentCarKitsIDs,
                               String[] newFactoriesCountries, String[] currentFactoriesCountries,
                               String isDeleteAutomobile);
}
