package TestProject.service;

import TestProject.domain.testEntity;

import java.util.List;

/**
 * Created by Alexandr on 15.05.2016.
 */
public interface serviceForTestEntity {
    testEntity createEntity (String name, int number, int year);
    void deleteEntity(String name);
    testEntity getByID (int id);
    testEntity getByName (String name);
    testEntity getByDescription (String description);
    List<testEntity> getAll ();
}
