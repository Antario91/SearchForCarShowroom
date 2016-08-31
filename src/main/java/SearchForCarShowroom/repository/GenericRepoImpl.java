package SearchForCarShowroom.repository;

import SearchForCarShowroom.domain.GenericRepo;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by AlexandrGoloborodko on 20.08.16.
 */
@Component
public class GenericRepoImpl<T> implements GenericRepo<T> {

    private Class<T> objClass;

    @Autowired
    private SessionFactory sessionFactory;

    public GenericRepoImpl(Class<T> objClass){
        this.objClass = objClass;
    }

    public GenericRepoImpl(){}

    @Override
    public void add(T obj) {
        sessionFactory.getCurrentSession()
                .saveOrUpdate(obj);
    }

    @Override
    public void delete(T obj) {
        sessionFactory.getCurrentSession()
                .delete(obj);
    }

    @Override
    public T getById(int id) {
        return sessionFactory.getCurrentSession()
                .get(objClass, id);
    }

    @Override
    public List getAllEntity() {
        return sessionFactory.getCurrentSession()
                .createCriteria(objClass)
                .list();
    }

    @Override
    public List<T> getByField(String field, String value) {
        return sessionFactory.getCurrentSession()
                .createCriteria(objClass)
                .add(Restrictions.ilike(field, "%" + value + "%"))
                .list();
    }

    @Override
    public T getOneByField(String field, String value) {
        return (T) sessionFactory.getCurrentSession()
                .createCriteria(objClass)
                .add(Restrictions.eq(field, value))
                .uniqueResult();
    }

//    public List<?> checkingDataBases (){
//        return sessionFactory.getCurrentSession()
//                .createQuery("show databases")
//                .list();
//    }
}
