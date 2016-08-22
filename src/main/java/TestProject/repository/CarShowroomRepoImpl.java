package TestProject.repository;

import TestProject.domain.CarShowroom;
import org.springframework.stereotype.Repository;

/**
 * Created by AlexandrGoloborodko on 21.08.16.
 */
@Repository
public class CarShowroomRepoImpl extends GenericRepoImpl<CarShowroom> implements CarShowroomRepo {
//    public CarShowroomRepoImpl(Class<CarShowroom> objClass) {
//        super(objClass);
//    }

    public CarShowroomRepoImpl(){
        super(CarShowroom.class);
    }

    @Override
    public CarShowroom getByName(String name) {
        return getOneByField("name", name);
    }
}
