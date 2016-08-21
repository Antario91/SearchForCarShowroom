package TestProject.repository;

import TestProject.domain.Automobile;

/**
 * Created by AlexandrGoloborodko on 20.08.16.
 */
public interface AutomobileRepo extends GenericRepo<Automobile> {
    Automobile getByModel (String model);

}
