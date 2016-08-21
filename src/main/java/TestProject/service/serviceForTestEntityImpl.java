package TestProject.service;

import TestProject.repository.repoForTestEntity;
import TestProject.domain.testEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Alexandr on 15.05.2016.
 */
@Service
@Transactional
public class serviceForTestEntityImpl implements serviceForTestEntity {

    @Autowired
    private repoForTestEntity RepoForTestEntity;

    @Override
    public testEntity createEntity(String name, int number, int year) {
        testEntity entity = new testEntity(name, number, year);
        RepoForTestEntity.add(entity);
        return entity;
    }

    @Override
    public void deleteEntity(String name) {
        List<testEntity> temp = RepoForTestEntity.getByName(name);
        Iterator<testEntity> itr = temp.iterator();
        testEntity tempEntity = null;
        while (itr.hasNext()) {
            tempEntity = itr.next();
            if (tempEntity.getName().equalsIgnoreCase(name)){
                break;
            }
        }
        RepoForTestEntity.delete(tempEntity);
    }

    @Override
    public testEntity getByID(int id) {
        return RepoForTestEntity.getById(id);
    }

    @Override
    public testEntity getByName(String name) {
        List<testEntity> temp = RepoForTestEntity.getByName(name);
        Iterator<testEntity> itr = temp.iterator();
        testEntity tempEntity = null;
        while (itr.hasNext()) {
            tempEntity = itr.next();
            if (tempEntity.getName().equalsIgnoreCase(name)){
                break;
            }
        }
        return tempEntity;
    }

    @Override
    public testEntity getByDescription(String description) {
        List<testEntity> temp = RepoForTestEntity.getByDescription(description);
        Iterator<testEntity> itr = temp.iterator();
        testEntity tempEntity = null;
        while (itr.hasNext()) {
            tempEntity = itr.next();
            if (!(tempEntity.getDescription().equalsIgnoreCase(description))){
                break;
            }

            if (itr.hasNext()){tempEntity = null;}
        }
        return tempEntity;
    }

    @Override
    public List<testEntity> getAll() {
        return RepoForTestEntity.getAllTestEntity();
    }
}
