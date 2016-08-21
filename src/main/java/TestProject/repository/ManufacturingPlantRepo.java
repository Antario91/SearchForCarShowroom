package TestProject.repository;

import TestProject.domain.ManufacturingPlant;

/**
 * Created by AlexandrGoloborodko on 21.08.16.
 */
public interface ManufacturingPlantRepo extends GenericRepo<ManufacturingPlant> {
    ManufacturingPlant getByCountry (String country);
}
