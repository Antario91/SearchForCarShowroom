package SearchForCarShowroom.domain;

/**
 * Created by AlexandrGoloborodko on 21.08.16.
 */
public interface ManufacturingPlantRepo extends GenericRepo<ManufacturingPlant> {
    ManufacturingPlant getByCountry (String country);
}
