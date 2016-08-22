package TestProject.DBInitializer;

import TestProject.domain.Automobile;
import TestProject.repository.AutomobileRepo;
import TestProject.service.creationService;
import TestProject.service.serviceForTestEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by AlexandrGoloborodko on 20.08.16.
 */
@Component
public class DBInitializer implements DBInitialize {

/*    @Autowired
    serviceForTestEntity service;

    @Override
    public void initDB() {
        if (service.getAll().size() == 0) {
            service.createEntity("Alexandr", 1, 1991);
            service.createEntity("Alexey", 2, 1983);
            service.createEntity("Eugene", 3, 1990);
        }
    }*/

    @Autowired
    creationService service;


    @Override
    public void initDB() {
        if (service.findAllAutomobile().size() == 0) {
            service.createAutomobile("Rapid", 77.0, 175.0, 195.0, 10.3, 5.4, 1635);
            service.createAutomobile("Octavia", 132.0, 280.0, 229.0, 7.4, 6.4, 1938);
            service.createAutomobile("Fabia", 81.0, 175.0, 196.0, 9.4, 4.8, 1609);

            service.createCarKit("Rapid", true, false, true, false, true, 350000);
            service.createCarKit("Rapid", false, false, false, false, false, 300000);
            service.createCarKit("Octavia", true, true, true, true, true, 500000);
            service.createCarKit("Fabia", false, true, true, true, false, 290000);

            service.createCarShowroom("EurocarDP", "Ukraine", "Dnipro", "Aeroportovskaya 17");
            service.createCarShowroom("SkodaCarDP", "Ukraine", "Dnipro", "Druzhby 3a");

            service.createManufacturingPlant("Ukraine");
            service.createManufacturingPlant("Czech Republic");
            service.createManufacturingPlant("China");

            service.addPlantToAutomobile("Rapid", "Ukraine");
            service.addPlantToAutomobile("Rapid", "Czech Republic");
            service.addPlantToAutomobile("Octavia", "Czech Republic");
            service.addPlantToAutomobile("Octavia", "China");
            service.addPlantToAutomobile("Fabia", "Ukraine");
            service.addPlantToAutomobile("Fabia", "China");

            service.addCarShowRoomToCarKitByCost(350000, 350000, "EurocarDP");
            service.addCarShowRoomToCarKitByCost(500000, 500000, "EurocarDP");
            service.addCarShowRoomToCarKitByCost(300000, 300000, "SkodaCarDP");
            service.addCarShowRoomToCarKitByCost(290000, 290000, "SkodaCarDP");
        }
    }
}
