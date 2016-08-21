package TestProject.repository;

import java.util.List;

/**
 * Created by AlexandrGoloborodko on 20.08.16.
 */
public interface GenericRepo<T> {
    void add(T obj);
    void delete(T obj);
    T getById(int id);
    List<T> getAllEntity();
    List<T> getByField(String field, String value);
    T getOneByField(String field, String value);
//    List<?> checkingDataBases ();
}
