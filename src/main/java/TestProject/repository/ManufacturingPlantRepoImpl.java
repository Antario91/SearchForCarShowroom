package TestProject.repository;

import TestProject.domain.ManufacturingPlant;
import org.springframework.stereotype.Repository;

/**
 * Created by AlexandrGoloborodko on 21.08.16.
 */
@Repository
public class ManufacturingPlantRepoImpl extends GenericRepoImpl<ManufacturingPlant> implements ManufacturingPlantRepo{
    public ManufacturingPlantRepoImpl(Class<ManufacturingPlant> objClass) {
        super(objClass);
    }

    public ManufacturingPlantRepoImpl(){
        super(ManufacturingPlant.class);
    }

    @Override
    public ManufacturingPlant getByCountry(String country) {
        return getOneByField("Country", country);
    }
}
