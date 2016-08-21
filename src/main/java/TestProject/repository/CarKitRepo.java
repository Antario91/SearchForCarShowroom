package TestProject.repository;

import TestProject.domain.CarKit;

import java.util.List;

/**
 * Created by AlexandrGoloborodko on 21.08.16.
 */
public interface CarKitRepo extends GenericRepo<CarKit> {
    List<CarKit> getByDescription (String description);
    List<CarKit> getByCost (int minCost, int maxCost);
}
