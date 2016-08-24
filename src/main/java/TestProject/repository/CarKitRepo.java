package TestProject.repository;

import TestProject.domain.CarKit;

import java.util.List;
import java.util.Map;

/**
 * Created by AlexandrGoloborodko on 21.08.16.
 */
public interface CarKitRepo extends GenericRepo<CarKit> {
    List<CarKit> getByDescription (String description);
    List<CarKit> getByCost (int minCost, int maxCost);
    List<CarKit> getByCostAndDescription (Map<String, Integer> processedPrice, String description);
    List<CarKit> getByAutoIDAndCostAndDescription (int id, Map<String, Integer> processedPrice, String description);
}
