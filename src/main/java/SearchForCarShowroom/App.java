package SearchForCarShowroom;


import SearchForCarShowroom.config.SpringConfig;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.Lob;
import java.sql.Blob;

/**
 * Hello world!
 *
 */

public class App 
{
    public static void main( String[] args ) {

        ApplicationContext context1 = new AnnotationConfigApplicationContext(SpringConfig.class);

        for (String a :context1.getBeanDefinitionNames()) {
            System.out.println(a);

        }
//
//
//        DBInitialize initializer = (DBInitialize) context1.getBean("DBInitializer");
//        initializer.initDB();
//
//        CreationService service = (CreationService) context1.getBean("CreationServiceImpl");

//        service.findManufacturingPlantByCountry("Ukraine");
//        Set<CarKit>  kit = service.findAllCarKitsForAutomobile("Fabia");
//        Iterator<CarKit> itr = kit.iterator();
//        while (itr.hasNext()){
//            CarKit temp = itr.next();
//            System.out.println(temp);
//            System.out.println();
//        }

/*
        serviceForTestEntity service = (serviceForTestEntity)context1.getBean("serviceForTestEntityImpl");
        *//*CreationService.createEntity("Alexandr",1,1991);
        CreationService.createEntity("Alexey",2,1983);
        CreationService.createEntity("Eugene",3,1990);*//*

//        CreationService.deleteEntity("Alexandr");

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
