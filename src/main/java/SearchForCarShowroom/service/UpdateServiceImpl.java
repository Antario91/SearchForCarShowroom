package SearchForCarShowroom.service;

import SearchForCarShowroom.domain.Automobile;
import SearchForCarShowroom.domain.CarKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
                creationService.addCarKitToAutomobile(autoModel, carKitForAdding.get(i).getId());
            }
        }
    }

    @Override
    public void createAutomobileAndAddCarKit(Automobile auto, int[] carKitIDs) {
        creationService.createAutomobile(auto.getModel(), auto.getMaxPower(), auto.getMaxTorque(), auto.getMaxSpeed(),
                auto.getAcceleration(), auto.getFuelConsumption(), auto.getWeight());
        if (carKitIDs != null) {
            for (int i = 0; i < carKitIDs.length; i++) {
                creationService.addCarKitToAutomobile(auto.getModel(), carKitIDs[i]);
            }
        }
    }

}
