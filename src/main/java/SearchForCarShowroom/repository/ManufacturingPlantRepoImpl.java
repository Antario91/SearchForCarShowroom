package SearchForCarShowroom.repository;

import SearchForCarShowroom.domain.ManufacturingPlant;
import SearchForCarShowroom.domain.ManufacturingPlantRepo;
import org.springframework.stereotype.Repository;

/**
 * Created by AlexandrGoloborodko on 21.08.16.
 */
@Repository
public class ManufacturingPlantRepoImpl extends GenericRepoImpl<ManufacturingPlant> implements ManufacturingPlantRepo {
    public ManufacturingPlantRepoImpl(Class<ManufacturingPlant> objClass) {
        super(objClass);
    }

    public ManufacturingPlantRepoImpl(){
        super(ManufacturingPlant.class);
    }

    @Override
    public ManufacturingPlant getByCountry(String country) {
        return getOneByField("country", country);
    }
}
