package TestProject;


import TestProject.config.springConfig;
import TestProject.DBInitializer.DBInitialize;
import TestProject.domain.CarKit;
import TestProject.service.creationService;
import TestProject.service.serviceForTestEntity;
import TestProject.domain.testEntity;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Hello world!
 *
 */

public class App 
{
    public static void main( String[] args ) {

        ApplicationContext context1 = new AnnotationConfigApplicationContext(springConfig.class);

        for (String a :context1.getBeanDefinitionNames()) {
            System.out.println(a);

        }


        DBInitialize initializer = (DBInitialize) context1.getBean("DBInitializer");
        initializer.initDB();

        creationService service = (creationService) context1.getBean("creationServiceImpl");
        service.findManufacturingPlantByCountry("Ukraine");
//        Set<CarKit>  kit = service.findAllCarKitsForAutomobile("Fabia");
//        Iterator<CarKit> itr = kit.iterator();
//        while (itr.hasNext()){
//            CarKit temp = itr.next();
//            System.out.println(temp);
//            System.out.println();
//        }

/*
        serviceForTestEntity service = (serviceForTestEntity)context1.getBean("serviceForTestEntityImpl");
        *//*creationService.createEntity("Alexandr",1,1991);
        creationService.createEntity("Alexey",2,1983);
        creationService.createEntity("Eugene",3,1990);*//*

//        creationService.deleteEntity("Alexandr");

        testEntity e = service.getByID(3);
        System.out.println(e);

        System.out.println();
        e = service.getByName("Alexey");
        System.out.println(e);

        System.out.println();
        List<testEntity> listOfAll = service.getAll();
        Iterator<testEntity> itr = listOfAll.iterator();
        while (itr.hasNext()){
            testEntity temp = itr.next();
            System.out.println(temp);
        }

        System.out.println();
        e = service.getByDescription("Alexandr%1%1991");
        if (e == null){
            System.out.println("Not found entity");
        } else {
            System.out.println(e);
        }*/
    }
}
