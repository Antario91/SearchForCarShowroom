package TestProject.repository;

import TestProject.domain.Automobile;
import org.springframework.stereotype.Repository;


/**
 * Created by AlexandrGoloborodko on 21.08.16.
 */
@Repository
public class AutomobileRepoImpl extends GenericRepoImpl<Automobile> implements AutomobileRepo{
//    public AutomobileRepoImpl(Class<Automobile> objClass) {
//        super(objClass);
//    }

    public AutomobileRepoImpl(){
        super(Automobile.class);
    }

    @Override
    public Automobile getByModel(String model) {
        return getOneByField("model", model);
    }
}
