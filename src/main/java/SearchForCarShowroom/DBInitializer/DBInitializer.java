package SearchForCarShowroom.DBInitializer;

import SearchForCarShowroom.service.CreationService;
import SearchForCarShowroom.service.SearchService;
import SearchForCarShowroom.service.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by AlexandrGoloborodko on 20.08.16.
 */
@Component
public class DBInitializer implements DBInitialize {

/*    @Autowired
    serviceForTestEntity createService;

    @Override
    public void initDB() {
        if (createService.getAll().size() == 0) {
            createService.createEntity("Alexandr", 1, 1991);
            createService.createEntity("Alexey", 2, 1983);
            createService.createEntity("Eugene", 3, 1990);
        }
    }*/

    @Autowired
    private CreationService createService;

    @Autowired
    private SearchService serviceSearch;

    @Autowired
    private UpdateService updateService;

    @Override
    public void initDB() {
        if (serviceSearch.findAllAutomobile().size() == 0) {
            createService.createAutomobile("Rapid", 77.0, 175.0, 195.0, 10.3, 5.4, 1635);
            createService.createAutomobile("Octavia", 132.0, 280.0, 229.0, 7.4, 6.4, 1938);
            createService.createAutomobile("Fabia", 81.0, 175.0, 196.0, 9.4, 4.8, 1609);

            createService.createCarKit("Rapid", true, false, true, false, true, 350000);
            createService.createCarKit("Rapid", false, false, false, false, false, 300000);
            createService.createCarKit("Octavia", true, true, true, true, true, 500000);
            createService.createCarKit("Fabia", false, true, true, true, false, 290000);

            createService.createCarShowroom("EurocarDP", "Ukraine", "Dnipro", "Aeroportovskaya 17");
            createService.createCarShowroom("SkodaCarDP", "Ukraine", "Dnipro", "Druzhby 3a");

            createService.createManufacturingPlant("Ukraine");
            createService.createManufacturingPlant("Czech Republic");
            createService.createManufacturingPlant("China");

            updateService.addPlantToAutomobile("Rapid", "Ukraine");
            updateService.addPlantToAutomobile("Rapid", "Czech Republic");
            updateService.addPlantToAutomobile("Octavia", "Czech Republic");
            updateService.addPlantToAutomobile("Octavia", "China");
            updateService.addPlantToAutomobile("Fabia", "Ukraine");
            updateService.addPlantToAutomobile("Fabia", "China");

            updateService.addCarShowRoomToCarKitByCost(350000, 350000, "EurocarDP");
            updateService.addCarShowRoomToCarKitByCost(500000, 500000, "EurocarDP");
            updateService.addCarShowRoomToCarKitByCost(300000, 300000, "SkodaCarDP");
            updateService.addCarShowRoomToCarKitByCost(290000, 290000, "SkodaCarDP");
        }
    }
}
