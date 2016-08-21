package TestProject.repository;

import TestProject.domain.testEntity;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Alexandr on 15.05.2016.
 */
@Repository
public class repoForTestEntityImpl implements repoForTestEntity {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(testEntity obj) {
        sessionFactory.getCurrentSession()
                .saveOrUpdate(obj);
    }

    @Override
    public void delete(testEntity obj) {
        sessionFactory.getCurrentSession()
                .delete(obj);
    }

    @Override
    public testEntity getById(int id) {
        return sessionFactory.getCurrentSession()
                .get(testEntity.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<testEntity> getAllTestEntity() {
        return sessionFactory.getCurrentSession()
                .createCriteria(testEntity.class)
                .list();
    }

    @Override
    public List<testEntity> getByName(String name) {
        return sessionFactory.getCurrentSession()
                .createCriteria(testEntity.class)
                .add(Restrictions.ilike("name", "%" + name + "%"))
                .list();
    }

    @Override
    public List<testEntity> getByDescription(String description) {
        return sessionFactory.getCurrentSession()
                .createCriteria(testEntity.class)
                .add(Restrictions.ilike("description", /*"%" +*/ description /*+ "%"*/))
                .list();
    }

    @Override
    public List<testEntity> getAllTestEntityFromSelect(String query) {
        return sessionFactory.getCurrentSession()
                .createQuery("select * from `entity !`")
                .list();
    }


}
