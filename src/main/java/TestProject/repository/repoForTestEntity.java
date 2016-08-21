package TestProject.repository;

import TestProject.domain.testEntity;

import java.util.List;

/**
 * Created by Alexandr on 15.05.2016.
 */
public interface repoForTestEntity {
    void add (testEntity obj);
    void delete (testEntity obj);
    testEntity getById (int id);
    List<testEntity> getAllTestEntity ();
    List<testEntity> getByName (String name);
    List<testEntity> getByDescription (String description);
    List<testEntity> getAllTestEntityFromSelect (String query);
}
