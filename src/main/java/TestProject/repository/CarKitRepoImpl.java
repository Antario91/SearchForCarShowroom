package TestProject.repository;

import TestProject.domain.CarKit;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by AlexandrGoloborodko on 21.08.16.
 */
@Repository
public class CarKitRepoImpl extends GenericRepoImpl<CarKit> implements CarKitRepo {

    @Autowired
    SessionFactory sessionFactory;

//    public CarKitRepoImpl(Class<CarKit> objClass) {
//        super(objClass);
//    }

    public CarKitRepoImpl(){
        super(CarKit.class);
    }

    @Override
    public List<CarKit> getByDescription(String description) {
        return sessionFactory.getCurrentSession()
                .createCriteria(CarKit.class)
                .add(Restrictions.ilike("description", description))
                .list();
    }

    @Override
    public List<CarKit> getByCost(int minCost, int maxCost) {
        return sessionFactory.getCurrentSession()
                .createCriteria(CarKit.class)
                .add(Restrictions.ge("cost", minCost))
                .add(Restrictions.le("cost", maxCost))
                .list();
    }

    @Override
    public List<CarKit> getByCostAndDescription(Map<String, Integer> processedPrice, String description) {
        return sessionFactory.getCurrentSession()
                .createCriteria(CarKit.class)
                .add(Restrictions.ge("cost", processedPrice.get("minCost")))
                .add(Restrictions.le("cost", processedPrice.get("maxCost")))
                .add(Restrictions.ilike("description", description))
                .list();
    }

    @Override
    public List<CarKit> getByAutoIDAndCostAndDescription(int id, Map<String, Integer> processedPrice, String description) {
        return sessionFactory.getCurrentSession()
                .createCriteria(CarKit.class)
                .add(Restrictions.eq("auto_ID", id))
                .add(Restrictions.ge("cost", processedPrice.get("minCost")))
                .add(Restrictions.le("cost", processedPrice.get("maxCost")))
                .add(Restrictions.ilike("description", description))
                .list();
    }


}
