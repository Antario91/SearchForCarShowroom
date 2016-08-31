package SearchForCarShowroom.service;

import SearchForCarShowroom.domain.Automobile;

/**
 * Created by Admin on 31.08.2016.
 */
public interface UpdateService {
    void updateAutomobileWithNewCarKits(String autoModel, int[] carKitIDs);
    void createAutomobileAndAddCarKit(Automobile auto, int[] carKitIDs);
}
